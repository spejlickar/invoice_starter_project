package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatisticsDTO;

import java.util.List;

public interface PersonService {

    /**
     * Creates a new person
     *
     * @param personDTO Person to create
     * @return newly created person
     */
    PersonDTO addPerson(PersonDTO personDTO);

    /**
     * <p>Sets hidden flag to true for the person with the matching [id]</p>
     * <p>In case a person with the passed [id] isn't found, the method <b>silently fails</b></p>
     *
     * @param id Person to delete
     */
    void removePerson(long id);

    /**
     * Fetches all non-hidden persons
     *
     * @return List of all non-hidden persons
     */
    List<PersonDTO> getAll();

    /**
     * varti DTO person
     * @param personId hledane Id
     * @return person DTO
     */

    PersonDTO getPersonById(long personId);

    /**
     * upravi osobu dle id osoby
     * @param personId id osoby
     * @param newPersonDTO DTO nove osoby
     * @return nove DTO vcetne id
     */
    PersonDTO editPersonById(long personId, PersonDTO newPersonDTO);

    /**
     * vyhleda faktury dle
     * @param identificationNumber
     * @return
     */
    List<InvoiceDTO> getPurchasesByIdentificationNumber(String identificationNumber);

    List<InvoiceDTO> getSalesByIdentificationNumber(String identificationNumber);

    List<PersonStatisticsDTO> getPersonsStatistics();

}
