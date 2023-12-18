
-- Creation des tables qui composent la BDD
CREATE TABLE users (
  user_id SERIAL PRIMARY KEY,
  user_email VARCHAR(200) NOT NULL,
  password VARCHAR(200) NOT NULL,
  balance DECIMAL(12,2) NOT NULL,   -- plafond de compte : 9.9 milliards
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL
  );

CREATE TABLE contacts (
  contact_id SERIAL PRIMARY KEY,
  origin_user_id INT NOT NULL,
  other_user_id INT NOT NULL,
  name_contact VARCHAR(200) NOT NULL,
  CONSTRAINT fk_contact_origin_user FOREIGN KEY (origin_user_id) REFERENCES users (user_id),
  CONSTRAINT fk_contact_other_user FOREIGN KEY (other_user_id) REFERENCES users (user_id)
  );
  
CREATE TABLE money_transactions (
  money_transaction_id SERIAL PRIMARY KEY,
  giver_id INT NOT NULL,
  receiver_id INT NOT NULL,
  description VARCHAR(200),
  amount DECIMAL(8,2) NOT NULL,   -- plafond de transfert : 99.9999 
  CONSTRAINT fk_moneytransaction_user_giver FOREIGN KEY (giver_id) REFERENCES users (user_id),
  CONSTRAINT fk_moneytransaction_user_receiver FOREIGN KEY (receiver_id) REFERENCES users (user_id)
  );
  
-- Remplissage table users
INSERT INTO users (user_email, password, balance, first_name, last_name)
  VALUES 
  ('userEmail1' , '$2a$12$kVlFNkyutY6qXRyT/JRdUusQLZp6k1MXS47eeKDJh3PBtgHNof2sO' , '5000.64' , 'fred' , 'smith'),
  ('userEmail2' , '$2a$12$Ko/Ai7TyQwfVAFYS42jfl.YeP01OpDIoU7b6spyMXz7FWbPC4XYWC' , '200.64' , 'mike' , 'smith'),
  ('userEmail3' , '$2a$12$xESGiGC8PVYoeMa3pC4TXOp/AYm8WVvuHqh5dTf6sbwN9c8nvNNRC' , '0.00' , 'cindy' , 'smith'),
  ('userEmail4' , '$2a$12$DxOiBwpuYZStOBfhzYvWWeEzZFcg96WLgMIOMwNLfkWPgtlf7qzb2' , '5000.00' , 'jack' , 'smith');

-- Remplissage table contacts
INSERT INTO contacts (origin_id, other_id, name_contact)
  VALUES 
  ('1' , '4' , 'jack - friend of fred'),
  ('1' , '2' , 'mike - friend of fred'),
  ('1' , '3' , 'cindy - friend of fred'),
  ('2' , '4' , 'jack - friend of mike'),
  ('2' , '3' , 'cindy - friend of mike');
  
-- Remplissage table money_transactions
INSERT INTO money_transactions (giver_id, receiver_id, description, amount)
  VALUES 
  ('1' , '2' , 'description 1' , '445.00'),
  ('2' , '3' , 'description 2' , '125.00'),
  ('3' , '4' , 'description 3' , '8954.78'),
  ('4' , '1' , 'description 4' , '4255.40');

