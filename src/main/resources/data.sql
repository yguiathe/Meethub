-- Initialize Settings table
INSERT INTO meethub_test.app_setting (ID, CODE, NAME, DESCRIPTION, DEFAULT_VALUE) VALUES (1, 'MCA', 'Minimum Contribution Amount', 'Minimum Contribution Amount', '500');
INSERT INTO meethub_test.app_setting (ID, CODE, NAME, DESCRIPTION, DEFAULT_VALUE) VALUES (2, 'MAB', 'Minimum Account Balance', 'Minimum Account Balance', '100000');
INSERT INTO meethub_test.app_setting (ID, CODE, NAME, DESCRIPTION, DEFAULT_VALUE) VALUES (3, 'MWA', 'Maximum Withdrawal Amount', 'Maximum Withdrawal Amount (Daily)', '5000');
INSERT INTO meethub_test.app_setting (ID, CODE, NAME, DESCRIPTION, DEFAULT_VALUE) VALUES (4, 'LIR', 'Loan Interest Rate', 'Loan Interest Rate', '3');
INSERT INTO meethub_test.app_setting (ID, CODE, NAME, DESCRIPTION, DEFAULT_VALUE) VALUES (5, 'SIR', 'Saving Interest Rate', 'Saving Interest Rate', '3');
INSERT INTO meethub_test.app_setting (ID, CODE, NAME, DESCRIPTION, DEFAULT_VALUE) VALUES (6, 'IIR', 'Minimum Loan Installment/Income Ratio', 'Minimum Income-Loan Installment Ratio', '30');
INSERT INTO meethub_test.app_setting (ID, CODE, NAME, DESCRIPTION, DEFAULT_VALUE) VALUES (7, 'KIR', 'Kind of Interest Rates', 'Kind of Interest Rates (Simple/Fixed/Compound)', '1');
INSERT INTO meethub_test.app_setting (ID, CODE, NAME, DESCRIPTION, DEFAULT_VALUE) VALUES (8, 'MLF', 'Maximum Loanable Funds', 'Percentage of total Balance available for loans', '85');
INSERT INTO meethub_test.app_setting (ID, CODE, NAME, DESCRIPTION, DEFAULT_VALUE) VALUES (9, 'MLT', 'Maximum Loan Term', 'Maximum Loan Term (in months)', '60');
INSERT INTO meethub_test.app_setting (ID, CODE, NAME, DESCRIPTION, DEFAULT_VALUE) VALUES (10, 'LGP', 'Loan Grace Period', 'Loan Grace Period (in months)', '1');
INSERT INTO meethub_test.app_setting (ID, CODE, NAME, DESCRIPTION, DEFAULT_VALUE) VALUES (11, 'IAF', 'Interest Accrual Frequency', 'Interest Accrual Frequency on Savings (yearly)', '2');
INSERT INTO meethub_test.app_setting (ID, CODE, NAME, DESCRIPTION, DEFAULT_VALUE) VALUES (12, 'DFA', 'Date of First Accrual', 'Date of First Interest Accrual on Savings', '');
INSERT INTO meethub_test.app_setting (ID, CODE, NAME, DESCRIPTION, DEFAULT_VALUE) VALUES (13, 'MHC', 'Maximum Headcount', 'Maximum Number of Individuals allowed to join the group', '60');
INSERT INTO meethub_test.app_setting (ID, CODE, NAME, DESCRIPTION, DEFAULT_VALUE) VALUES (14, 'EPO', 'Early Payoff Allowed', 'Early Payoff Allowed', 'True');
INSERT INTO meethub_test.app_setting (ID, CODE, NAME, DESCRIPTION, DEFAULT_VALUE) VALUES (15, 'CRS', 'Contribution Redistribution Schedule', 'Contribution Redistribution Schedule (Round-Robin/Auctions)', '1');
INSERT INTO meethub_test.app_setting (ID, CODE, NAME, DESCRIPTION, DEFAULT_VALUE) VALUES (16, 'NOA', 'Number Of Approvers', 'Number Of Approvers', '3');

-- Initialize Violations Types table
INSERT INTO meethub_test.app_violation_type (ID, CODE, NAME, DESCRIPTION) VALUES ('1', 'LLP', 'Late Payment', 'Late Payment or Delinquency');
INSERT INTO meethub_test.app_violation_type (ID, CODE, NAME, DESCRIPTION) VALUES ('2', 'MNS', 'Meeting No-Show', 'Failed to Attend Meeting');
INSERT INTO meethub_test.app_violation_type (ID, CODE, NAME, DESCRIPTION) VALUES ('3', 'ONS', 'Team Outing No-Show', 'Failure to Attend Group Event');
INSERT INTO meethub_test.app_violation_type (ID, CODE, NAME, DESCRIPTION) VALUES ('4', 'SLM', 'Late Arrival', 'Late Arrival to a Gathering');
INSERT INTO meethub_test.app_violation_type (ID, CODE, NAME, DESCRIPTION) VALUES ('5', 'EAC', 'Early Account Closure', 'Early Account Closure');
INSERT INTO meethub_test.app_violation_type (ID, CODE, NAME, DESCRIPTION) VALUES ('6', 'XDB', 'Excessive Disturbance', 'Excessive Disturbance (Shouting/Chatting)');
INSERT INTO meethub_test.app_violation_type (ID, CODE, NAME, DESCRIPTION) VALUES ('7', 'AOR', 'Any Other Violations', 'Any other failures to comply with group policies');
INSERT INTO meethub_test.app_violation_type (ID, CODE, NAME, DESCRIPTION) VALUES ('8', 'FDM', 'Failure to Deliver Duties', 'Failure to deliver on assigned task');
INSERT INTO meethub_test.app_violation_type (ID, CODE, NAME, DESCRIPTION) VALUES ('9', 'ERL', 'Early Repayment of Loan', 'Early Repayment of Loan');

-- Initialize Roles table
INSERT INTO meethub_test.app_role (ID, NAME) VALUES (1, 'ROLE_ADMIN');
INSERT INTO meethub_test.app_role (ID, NAME) VALUES (2, 'ROLE_USER');