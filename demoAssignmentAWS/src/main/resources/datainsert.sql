create table info(
	customerID int Not Null,
	branchID VARCHAR(40) Not Null,
	accountID int Not Null
);
insert into info(customerID ,accountID,branchID)values(?,?,?);

