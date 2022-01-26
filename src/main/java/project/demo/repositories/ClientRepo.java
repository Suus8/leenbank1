package project.demo.repositories;

import project.demo.model.*;

import java.util.ArrayList;
import java.util.List;

public class ClientRepo {
    //region ATTRIBUTES
    private List<AbstractClient> clients;
    //endregion

    //region CONSTRUCTOR

    public ClientRepo() {
        this.clients = new ArrayList<>();

        // DUMMY DATA // TODO: change to import of mock data
        // Create clients

        // --------- PERSON ----------

        Name name1 = new Name("Monique", "Jansen");
        Login login1 = new Login("moniqueketje", "wachtwoord123");
        ClientDetails clientDetails1 = new ClientDetails("moniqueketje@home.nl", "0611666", "Nachtegaalstraat", 57, "Rood", "2025VH", "Haarlem");
        PersonDetails personDetails1 = new PersonDetails(name1, "1990-01-01");
        Person person1 = new Person(822518007L, login1, clientDetails1, personDetails1);
        this.clients.add(person1);

        Name name2 = new Name("Henk", "Jansen");
        Login login2 = new Login("henk", "ditpwraadtniemand!");
        ClientDetails clientDetails2 = new ClientDetails("henk_jansen@home.nl", "0611666", "Brouwersplein", 2, "ZW", "2013PE", "Haarlem");
        PersonDetails personDetails2 = new PersonDetails(name2, "1990-12-31");
        Person person2 = new Person(292164637L, login2, clientDetails2, personDetails2);
        this.clients.add(person2);

        Name name3 = new Name("Bram", "Joos");
        Login login3 = new Login("brammietje", "bambambam");
        ClientDetails clientDetails3 = new ClientDetails("bram.joos@home.nl", "0611666", "Witterstraat", 124, "bis A", "9401SE", "Assen");
        PersonDetails personDetails3 = new PersonDetails(name3, "1995-12-04");
        Person person3 = new Person(768126274L, login3, clientDetails3, personDetails3);
        this.clients.add(person3);

        Name name4 = new Name("Hannes", "van Bramer");
        Login login4 = new Login("Hanns", "abcde");
        ClientDetails clientDetails4 = new ClientDetails("hannes1985@home.nl", "0611666", "Gangboord", 90, "F 4", "4902CA", "Oosterhout");
        PersonDetails personDetails4 = new PersonDetails(name4, "1985-04-13");
        Person person4 = new Person(645392637L, login4, clientDetails4, personDetails4);
        this.clients.add(person4);

        Name name5 = new Name("Sven", "van Woert");
        Login login5 = new Login("svne", "12345");
        ClientDetails clientDetails5 = new ClientDetails("sven.v.woert@home.nl", "0611666", "Sint Hubertuslaan", 95, "a", "6129LC", "Urmond");
        PersonDetails personDetails5 = new PersonDetails(name5, "1970-06-21");
        Person person5 = new Person(123456789L, login5, clientDetails5, personDetails5);
        this.clients.add(person5);

        Name name6 = new Name("Marta", "Schilder");
        Login login6 = new Login("schildertje", "lampje");
        ClientDetails clientDetails6 = new ClientDetails("marta-schilder@home.nl", "0611666", "Vincent van Goghstraat", 106, "K204", "1318KV", "Almere");
        PersonDetails personDetails6 = new PersonDetails(name6, "1952-09-06");
        Person person6 = new Person(976117730L, login6, clientDetails6, personDetails6);
        this.clients.add(person6);


        // --------- EMPLOYEE ----------

        Name nameEmployee1 = new Name("Mark", "de Vries");
        Login loginEmployee1 = new Login("markiiii", "brabant");
        ClientDetails clientDetailsEmployee1 = new ClientDetails("markiiii_degekste@home.nl", "0611666", "Molenweg", 3, null, "1234AB", "Eindhoven");
        PersonDetails personDetailsEmployee1 = new PersonDetails(nameEmployee1, "1992-01-01"); // TODO find out why it saves twice
        Employee employee1 = new Employee(987654321L, loginEmployee1, clientDetailsEmployee1, personDetailsEmployee1, JobTitle.ACCOUNT_MANAGER);
        this.clients.add(employee1);

        Name nameEmployee2 = new Name("Egbert", "Vandall");
        Login loginEmployee2 = new Login("eggie", "vragile");
        ClientDetails clientDetailsEmployee2 = new ClientDetails("egbert1990@home.nl", "0611666", "Padvinderslaan", 199, "a", "3708BS", "Zeist");
        PersonDetails personDetailsEmployee2 = new PersonDetails(nameEmployee2, "1990-10-31");
        Employee employee2 = new Employee(867363329L, loginEmployee2, clientDetailsEmployee2, personDetailsEmployee2, JobTitle.BUSINESS_MANAGER);
        this.clients.add(employee2);

        Name nameEmployee3 = new Name("Anna", "Verkuilen");
        Login loginEmployee3 = new Login("anna", "09876");
        ClientDetails clientDetailsEmployee3 = new ClientDetails("Anna_Verkuilen@home.nl", "0611666", "Julianastraat", 153, "b", "4651BX", "Steenbergen");
        PersonDetails personDetailsEmployee3 = new PersonDetails(nameEmployee3, "1947-03-03");
        Employee employee3 = new Employee(724388120L, loginEmployee3, clientDetailsEmployee3, personDetailsEmployee3, JobTitle.ACCOUNT_MANAGER);
        this.clients.add(employee3);

        Name nameEmployee4 = new Name("Janneke", "Linden");
        Login loginEmployee4 = new Login("Jantje", "mooo");
        ClientDetails clientDetailsEmployee4 = new ClientDetails("janneke.linden@home.nl", "0611666", "Prins Bernhardlaan", 138, null, "6721DN", "Bennekom");
        PersonDetails personDetailsEmployee4 = new PersonDetails(nameEmployee4, "1957-02-14");
        Employee employee4 = new Employee(342931759L, loginEmployee4, clientDetailsEmployee4, personDetailsEmployee4, JobTitle.CONSUMER_MANAGER);
        this.clients.add(employee4);

        Name nameEmployee5 = new Name("Jan", "Broeren");
        Login loginEmployee5 = new Login("jan1234", "4567");
        ClientDetails clientDetailsEmployee5 = new ClientDetails("jan-broeren@home.nl", "0611666", "Loenermark", 3, null, "1025SM", "Amsterdam");
        PersonDetails personDetailsEmployee5 = new PersonDetails(nameEmployee5, "1996-12-11");
        Employee employee5 = new Employee(750435105L, loginEmployee5, clientDetailsEmployee5, personDetailsEmployee5, JobTitle.ACCOUNT_MANAGER);
        this.clients.add(employee5);

        Name nameEmployee6 = new Name("Natalie", "Peterman");
        Login loginEmployee6 = new Login("naatt", "vlinder");
        ClientDetails clientDetailsEmployee6 = new ClientDetails("natalie-peterman@home.nl", "0611666", "Traaiweg", 76, null, "3956NP", "Leersum");
        PersonDetails personDetailsEmployee6 = new PersonDetails(nameEmployee6, "1971-02-21");
        Employee employee6 = new Employee(196492622L, loginEmployee6, clientDetailsEmployee6, personDetailsEmployee6, JobTitle.CONSUMER_MANAGER);
        this.clients.add(employee6);


        // --------- COMPANY --------

        Login loginCompany1 = new Login("company1", "BakkerRobin1212((353001");
        ClientDetails clientDetailsCompany1 = new ClientDetails("info@bakkerrobin.nl", "0611220001", "Bergstraat", 1, "Z", "1234AB", "Eindhoven");
        CompanyDetails companyDetails1 = new CompanyDetails("Bakker Robin", Sector.RETAIL);
        Company company1 = new Company(11661873001L, loginCompany1, clientDetailsCompany1, companyDetails1);
        company1.setRegistrationCodePinTerminal(11223344);
        employee1.setJobTitle(JobTitle.ACCOUNT_MANAGER);
        this.clients.add(company1);

        Login loginCompany2 = new Login("company2", "Sp4R3lax123456002");
        ClientDetails clientDetailsCompany2 = new ClientDetails("info@sparelax.com", "0611220002", "Parallelweg", 2, "Z", "4849PA", "Dorst");
        CompanyDetails companyDetails2 = new CompanyDetails("SPA Relax", Sector.CULTURE_SPORTS_RECREATION);
        Company company2 = new Company(86750821802L, loginCompany2, clientDetailsCompany2, companyDetails2);
        this.clients.add(company2);

        Login loginCompany3 = new Login("company3", "NlZ0rgSDAD1234003");
        ClientDetails clientDetailsCompany3 = new ClientDetails("info@nlzorg.nl", "0611220003", "Nachtwachtplein", 3, "Z", "7242CT", "Lochem");
        CompanyDetails companyDetails3 = new CompanyDetails("NL Zorg", Sector.HEALTHCARE);
        Company company3 = new Company(35179726903L, loginCompany3, clientDetailsCompany3, companyDetails3);
//        company3.setAccountManager(employee3); TODO find out why an extra employee record is created
        this.clients.add(company3);

        Login loginCompany4 = new Login("company4", "B4rryF4rm(*&(SDD1234004");
        ClientDetails clientDetailsCompany4 = new ClientDetails("info@barryfarm.nl", "0611220004", "Beukenstuklaan", 4, "Z", "3903DN", "Veenendaal");
        CompanyDetails companyDetails4 = new CompanyDetails("Berry Farm", Sector.AGRICULTURE_HORTICULTURE);
        Company company4 = new Company(68256396004L, loginCompany4, clientDetailsCompany4, companyDetails4);
//        company4.setAccountManager(employee5);
        this.clients.add(company4);

        Login loginCompany5 = new Login("company5", "BoerderijH3ndrik!!005");
        ClientDetails clientDetailsCompany5 = new ClientDetails("info@boerderijhendrik.nl", "0611220005", "Beukenstuklaan", 5, "Z", "3903DN", "Veenendaal");
        CompanyDetails companyDetails5 = new CompanyDetails("Boerderij Hendrik", Sector.AGRICULTURE_HORTICULTURE);
        Company company5 = new Company(23256396005L, loginCompany5, clientDetailsCompany5, companyDetails5);
        this.clients.add(company5);

        Login loginCompany6 = new Login("company6", "PaultjuhPoultry11//!!006");
        ClientDetails clientDetailsCompany6 = new ClientDetails("info@paulspoultry.nl", "0611220006", "Beukenstuklaan", 6, "Z", "3903DN", "Veenendaal");
        CompanyDetails companyDetails6 = new CompanyDetails("Paul's Poultry", Sector.AGRICULTURE_HORTICULTURE);
        Company company6 = new Company(23253391106L, loginCompany6, clientDetailsCompany6, companyDetails6);
        this.clients.add(company6);

        Login loginCompany7 = new Login("company7", "BelastingBert2255//!!007");
        ClientDetails clientDetailsCompany7 = new ClientDetails("info@belastingadvizeur.nl", "0611220007", "Stadstraat", 7, "Z", "1110NB", "Amsterdam");
        CompanyDetails companyDetails7 = new CompanyDetails("Belastingadviseur Bert", Sector.FINANCE);
        Company company7 = new Company(23255391107L, loginCompany7, clientDetailsCompany7, companyDetails7);
        this.clients.add(company7);

        Login loginCompany8 = new Login("company8", "BouwvakkerBart1/*@!008");
        ClientDetails clientDetailsCompany8 = new ClientDetails("info@bouwvakkerbart.nl", "0611220008", "Parklaan", 8, "Z", "2222AN", "Schiedam");
        CompanyDetails companyDetails8 = new CompanyDetails("Bouwvakker Bart", Sector.CONSTRUCTION);
        Company company8 = new Company(23252391108L, loginCompany8, clientDetailsCompany8, companyDetails8);
        this.clients.add(company8);

        Login loginCompany9 = new Login("company9", "MilieuMax11/aas/!!009");
        ClientDetails clientDetailsCompany9 = new ClientDetails("info@milieuadvies.nl", "0611220009", "Hovenierslaan", 9, "Z", "3333DN", "Spaarndam");
        CompanyDetails companyDetails9 = new CompanyDetails("Milieu adviseur Max", Sector.ENERGY_WATER_ENVIRONMENT);
        Company company9 = new Company(23254391109L, loginCompany9, clientDetailsCompany9, companyDetails9);
        this.clients.add(company9);

        Login loginCompany10 = new Login("company10", "SalarisSarah11345/!!010");
        ClientDetails clientDetailsCompany10 = new ClientDetails("info@sarah.nl", "0611220010", "Steenlaan", 10, "Z", "5552DN", "Rosmalen");
        CompanyDetails companyDetails10 = new CompanyDetails("Salarisadministratie Sarah", Sector.BUSINESS_SERVICES);
        Company company10 = new Company(23258391110L, loginCompany10, clientDetailsCompany10, companyDetails10);
        this.clients.add(company10);

        Login loginCompany11 = new Login("company11", "GastheerGert11345/!!011");
        ClientDetails clientDetailsCompany11 = new ClientDetails("info@gastheergert.nl", "0611220010", "Waterweg", 11, "Z", "5552DN", "Rosmalen");
        CompanyDetails companyDetails11 = new CompanyDetails("Gastheer Gert", Sector.HOSPITALITY);
        Company company11 = new Company(23258391111L, loginCompany11, clientDetailsCompany11, companyDetails11);
        this.clients.add(company11);

        Login loginCompany12 = new Login("company12", "DeveloperDave345/!!12");
        ClientDetails clientDetailsCompany12 = new ClientDetails("info@developerdave.nl", "0611220012", "Bomenlaan", 12, "Z", "5552DN", "Rosmalen");
        CompanyDetails companyDetails12 = new CompanyDetails("Software Engineer Dave", Sector.ICT_AND_MEDIA);
        Company company12 = new Company(23258391112L, loginCompany12, clientDetailsCompany12, companyDetails12);
        this.clients.add(company12);

        Login loginCompany13 = new Login("company13", "MachinebouwMaarten11345/!!13");
        ClientDetails clientDetailsCompany13 = new ClientDetails("info@m-machinebouw.nl", "0611220013", "Busstraat", 13, "Z", "5552DN", "Rosmalen");
        CompanyDetails companyDetails13 = new CompanyDetails("Maarten's Machines", Sector.INDUSTRIAL);
        Company company13 = new Company(23258391113L, loginCompany13, clientDetailsCompany13, companyDetails13);
        this.clients.add(company13);

        Login loginCompany14 = new Login("company14", "PakePakjesService11345/!!14");
        ClientDetails clientDetailsCompany14 = new ClientDetails("info@pake-pakjes.nl", "0611220014", "Burghoven", 14, "Z", "5552DN", "Rosmalen");
        CompanyDetails companyDetails14 = new CompanyDetails("Pakketbezorgservice Pake", Sector.LOGISTICS);
        Company company14 = new Company(23258391114L, loginCompany14, clientDetailsCompany14, companyDetails14);
        this.clients.add(company14);

        Login loginCompany15 = new Login("company15", "WhatWeDoNoBodyKnows!!15");
        ClientDetails clientDetailsCompany15 = new ClientDetails("info@nobodyknows.nl", "0611220015", "Takkenberg", 15, "Z", "5552DN", "Rosmalen");
        CompanyDetails companyDetails15 = new CompanyDetails("Nobody Knows B.V.", Sector.OTHER);
        Company company15 = new Company(23258391115L, loginCompany15, clientDetailsCompany15, companyDetails15);
        this.clients.add(company15);

        Login loginCompany16 = new Login("company16", "BakkerRalf/!!16");
        ClientDetails clientDetailsCompany16 = new ClientDetails("info@bakker-ralf.nl", "0611220016", "Industrieweg", 16, "Z", "5552DN", "Rosmalen");
        CompanyDetails companyDetails16 = new CompanyDetails("Bakker Ralf", Sector.RETAIL);
        Company company16 = new Company(23258391116L, loginCompany16, clientDetailsCompany16, companyDetails16);
        this.clients.add(company16);

        Login loginCompany17 = new Login("company17", "GroothandelGrover/!!17");
        ClientDetails clientDetailsCompany17 = new ClientDetails("info@groothandel-grover.nl", "0611220017", "Industrieweg", 17, "Z", "5552DN", "Rosmalen");
        CompanyDetails companyDetails17 = new CompanyDetails("Groothandel Grover", Sector.WHOLESALE);
        Company company17 = new Company(23258391117L, loginCompany17, clientDetailsCompany17, companyDetails17);
        this.clients.add(company17);

    }

    //endregion

    //region GETTERS & SETTERS

    public List<AbstractClient> getClients() {
        return clients;
    }

    //endregion

    //region TOSTRING, HASH, EQUALS, COMPARE

    //endregion
}
