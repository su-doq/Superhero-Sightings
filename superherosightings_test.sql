drop database superherosightings_test;
create database if not exists SuperheroSightings_test;
use SuperheroSightings_test;

create table Superpower(
	SuperpowerID int not null auto_increment,
    SuperpowerName varchar(50) not null,
    SuperpowerDescription varchar(100) not null,
    primary key (SuperpowerID)
    

) engine=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

create table Superbeing(
	SuperbeingID int not null AUTO_INCREMENT,
    SuperbeingAlias varchar(50) not null,
    SuperbeingFirstName varchar(50) null,
    SuperbeingLastName varchar(50) null,
    SuperbeingDescription text null,
    primary key(SuperbeingID)

) engine=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

create table SuperbeingPowerBridge(
	SuperbeingID int not null,
    SuperpowerID int not null,
    primary key(SuperbeingID, SuperpowerID),
    foreign key(SuperbeingID) references superbeing(SuperbeingID),
    foreign key (SuperpowerID) references superpower(SuperpowerID)
)engine=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

create table Address(
	AddressID int not null AUTO_INCREMENT,
    street varchar(70) null,
    city varchar(50) null,
    zip varchar(15) null,
    state varchar(50) null,
    country varchar(50) null,
    latitude DECIMAL(10, 8) NULL,
    longitude DECIMAL(11, 8) NULL,
    planet varchar(100) null,
    galaxy varchar(100) null,
    multiverse varchar(100) null,
    primary key(AddressID)

) engine=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

create table Location(
	LocationID int not null AUTO_INCREMENT,
    LocationName varchar(100) not null,
    LocationDescription varchar(200) null,
    AddressID int null,
    primary key(LocationID),
    foreign key (AddressID) references Address(AddressID)


) engine=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

create table Sighting(
	SightingID int not null AUTO_INCREMENT,
    SightingDate date not null,
    SightingDescription varchar(150) not null,
    SuperbeingID int not null,
    LocationID int not null,
    Primary key (SightingID),
    foreign key (SuperbeingID) references Superbeing(SuperbeingID),
    foreign key (LocationID) references Location(LocationID)
)engine=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

create table Organization(
	OrganizationID int not null AUTO_INCREMENT,
    OrganizationName varchar(50) null,
    OrganizationDescription varchar(50) null,
    LocationID int null,
    primary key (OrganizationID),
    foreign key (LocationID) references Location(LocationID)
    

)engine=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;


create table Member(
	MemberID int not null AUTO_INCREMENT,
	OrganizationID int not null,
	SuperbeingID int not null,
	StartDate date null,
    EndDate date null,
	primary key (MemberID),
    foreign key (OrganizationID) references Organization(OrganizationID),
    foreign key (SuperbeingID) references Superbeing(SuperbeingID)


)engine=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;
