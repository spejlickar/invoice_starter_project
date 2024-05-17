/*  _____ _______         _                      _
 * |_   _|__   __|       | |                    | |
 *   | |    | |_ __   ___| |___      _____  _ __| | __  ___ ____
 *   | |    | | '_ \ / _ \ __\ \ /\ / / _ \| '__| |/ / / __|_  /
 *  _| |_   | | | | |  __/ |_ \ V  V / (_) | |  |   < | (__ / /
 * |_____|  |_|_| |_|\___|\__| \_/\_/ \___/|_|  |_|\_(_)___/___|
 *                                _
 *              ___ ___ ___ _____|_|_ _ _____
 *             | . |  _| -_|     | | | |     |  LICENCE
 *             |  _|_| |___|_|_|_|_|___|_|_|_|
 *             |_|
 *
 *   PROGRAMOVÁNÍ  <>  DESIGN  <>  PRÁCE/PODNIKÁNÍ  <>  HW A SW
 *
 * Tento zdrojový kód je součástí výukových seriálů na
 * IT sociální síti WWW.ITNETWORK.CZ
 *
 * Kód spadá pod licenci prémiového obsahu a vznikl díky podpoře
 * našich členů. Je určen pouze pro osobní užití a nesmí být šířen.
 * Více informací na http://www.itnetwork.cz/licence
 */
package cz.itnetwork.entity.repository;

import cz.itnetwork.dto.PersonStatisticsDTO;
import cz.itnetwork.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    /**
     * Najde fakturu dle jestli jsou skrite(hidden)
     * @param hidden jsou/nejso skrite
     * @return seznam vsech faktur dle skriti
     */
    List<PersonEntity> findByHidden(boolean hidden);

    /**
     * vrati seznam lidi dle identifikacniho cisla (identificationNumber)
     * @param identificationNumber identifikacni cislo osoby
     * @return seznam lidi del identifikacniho cisla
     */
    List<PersonEntity> findByIdentificationNumber(String identificationNumber);

    /**
     * vypis statistik
     * @return vypiše statisku osob a jejich vyfakturované ceny
     */
    @Query(value = "SELECT NEW cz.itnetwork.dto.PersonStatisticsDTO( p.id,p.name,IFNULL(SUM(i.price),0) )" +
        " FROM person p LEFT JOIN invoice i ON p.id = seller WHERE p.hidden = 0 GROUP BY p.identificationNumber")
    List<PersonStatisticsDTO> getPersonStatistics();

}
