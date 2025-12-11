package cz.itnetwork.service;

import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.entity.statistics.PersonStatistics;

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
     * Fetch one person by id
     *
     * @param personId Person to show
     * @return Selected person
     * */
    PersonDTO getPerson(long personId);

    /**
     * Edit person
     *
     * @param personId Person to edit
     * @param personDTO Edited person data
     * @return Saved edited person
     * */
    PersonDTO editPerson(long personId, PersonDTO personDTO);

    /**
     * Get statistics of all persons
     * */
    List<PersonStatistics> getStatistics();
}
