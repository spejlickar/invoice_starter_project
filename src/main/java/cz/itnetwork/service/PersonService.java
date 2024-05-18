package cz.itnetwork.service;

import cz.itnetwork.dto.IcDTO;
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
     * vrati IČ unikatni seznam všech osob i skritých
     * @return IČ unikatni seznam všech osob i skritých
     */

    List<IcDTO> getAllIc();

    /**
     * vrati DTO person dle id, v pripade neexisstujici osoby, se vyvola vyjimka
     * @param personId hledane Id osoby
     * @return person DTO
     */
    PersonDTO getPersonById(long personId);

    /**
     * upravi osobu dle id osoby a dat z DTO
     * @param personId upravovana id osoba
     * @param newPersonDTO DTO nove osoby
     * @return nove DTO vcetne id
     */
    PersonDTO editPersonById(long personId, PersonDTO newPersonDTO);

    /**
     * vyhledá přijaté faktury dle identifikačního čísla
     * @param identificationNumber identifikační číslo
     * @return seznam přijatých faktur
     */
    List<InvoiceDTO> getPurchasesByIdentificationNumber(String identificationNumber);
    /**
     * vyhledá vydané faktury dle identifikačního čísla
     * @param identificationNumber identifikační číslo
     * @return seznam vydaných faktur
     */
    List<InvoiceDTO> getSalesByIdentificationNumber(String identificationNumber);

    /**
     * vypis statistik
     * @return vypiše statisku osob a jejich vyfakturované ceny
     */
    List<PersonStatisticsDTO> getPersonsStatistics();

}
