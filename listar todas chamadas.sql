SELECT u.id as 'Aluno', t.nome, c.data from chamada c 
INNER JOIN horario ON horario.id = c.horario_id 
INNER JOIN turma t ON horario.turma_id=t.id
INNER JOIN user u ON horario.user_id = u.id;