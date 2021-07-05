package com.projectAPIREST.projectREST.services;

import com.projectAPIREST.projectREST.dto.mapper.PersonMapper;
import com.projectAPIREST.projectREST.dto.request.PersonDTO;
import com.projectAPIREST.projectREST.dto.response.MessageResponseDTO;
import com.projectAPIREST.projectREST.entity.Person;
import com.projectAPIREST.projectREST.repository.PersonRepository;
import com.projectAPIREST.projectREST.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.projectAPIREST.projectREST.utils.PersonUtils.createFakeDTO;
import static com.projectAPIREST.projectREST.utils.PersonUtils.createFakeEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;

    @Mock
    private PersonMapper personMapper;

    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSuccessSaveMessage() {
        PersonDTO personDTO = createFakeDTO();
        Person expectedSavedPerson = createFakeEntity();

        when(personMapper.toModel(personDTO)).thenReturn(expectedSavedPerson);
        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

        MessageResponseDTO expectedSuccessMessage = createExpectedSuccessMessage(expectedSavedPerson.getId());
        MessageResponseDTO successMessage = personService.createPerson(personDTO);

        assertEquals(expectedSuccessMessage, successMessage);

    }

    private MessageResponseDTO createExpectedSuccessMessage(Long savedPersonId) {
        return MessageResponseDTO
                .builder()
                .message("Person successfully created with ID " + savedPersonId)
                .build();
    }
}
