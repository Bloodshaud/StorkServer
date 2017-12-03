--
-- Created on 27-11-2017
-- By Johannes Ernstsen
--

-- DML
INSERT INTO users (id, name, mail, password) VALUES
  (1, 'Johannes', 'Ernstsen.johannes@gmail.com', 'kodeord1'),
  (2, 'Morten', 'mortens.email.som.jeg.ikke.kan@gmail.com', 'kodeord2'),
  (3, 'Mathias', 'Mathias.mail.som.jeg.heller.ikke.lige.kan@gmail.com', 'kodeord3');

INSERT INTO groups (id, name) VALUES
  (1, 'firstGroup'),
  (2, 'secondGroupd');

INSERT INTO users_groups (userId, groupId) VALUES
  (1, 1),
  (2, 1),
  (2, 2),
  (3, 2);

INSERT INTO friends (userId, friendId) VALUES
  (1, 2),
  (1, 3),
  (2, 3);