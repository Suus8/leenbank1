/**
 * Created by abuzer.alaca on 17/01/2022
 **/


package project.demo.service;

import org.springframework.stereotype.Service;
import project.demo.dto.ClientDto;
import project.demo.model.*;
import project.demo.repositories.IAbstractClientRepo;
import project.demo.repositories.ILoginRepository;
import project.demo.repositories.IPersonDetailRepo;
import project.demo.utils.UserNotFountException;
import project.demo.utils.WrongPasswordException;

import java.util.Arrays;
import java.util.Optional;

@Service
public class LoginService {
    private ILoginRepository loginRepository;
    private IAbstractClientRepo abstractClientRepo;
    private IPersonDetailRepo personDetailRepo;


    public LoginService(ILoginRepository loginRepository, IAbstractClientRepo abstractClientRepo, IPersonDetailRepo personDetailRepo) {
        this.loginRepository = loginRepository;
        this.abstractClientRepo = abstractClientRepo;
        this.personDetailRepo = personDetailRepo;
    }

    private boolean checkLogin(Login login) {
        return loginRepository.existsByUsername(login.getUsername());
    }

    public ClientDto login(Login login) throws UserNotFountException, WrongPasswordException {
        ClientDto clientDto = new ClientDto();
        if (!checkLogin(login)) throw new UserNotFountException("User not found");
        if (!checkUserNameAndPassword(login)) throw new WrongPasswordException();
        Long loginId = loginRepository.getIdByUsername(login.getUsername());
        AbstractClient client = abstractClientRepo.findByLoginId(loginId);
        Long personId = abstractClientRepo.findPersonDetailIdById(client.getId());
        Optional<PersonDetails> personDetails = personDetailRepo.findById(personId);

        clientDto.setId(client.getId());
        clientDto.setName(personDetails.map(details -> details.getFullName().getFullNameAsString()).orElse(null));
        clientDto.setUsername(login.getUsername());
        if (client instanceof Person) clientDto.setClientType("Person");
        else if (client instanceof Company) clientDto.setClientType("Company");
        else clientDto.setClientType("Employee");
        return clientDto;
    }

    private boolean checkUserNameAndPassword(Login login) {
        return loginRepository.existsByUsernameAndPassword(login.getUsername(), login.getPassword());
    }
}
