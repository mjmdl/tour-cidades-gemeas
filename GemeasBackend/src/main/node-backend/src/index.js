const express = require('express');
const cors = require('cors');
const { Client } = require('pg');
require('dotenv').config();

const app = express();


const client = new Client({
  connectionString: process.env.DATABASE_URL || 'postgresql://neondb_owner:npg_Uz3dB4GKDsMi@ep-rough-leaf-ac2yr7fq-pooler.sa-east-1.aws.neon.tech/neondb?sslmode=require'
});


client.connect()
  .then(() => console.log('Conectado ao banco de dados'))
  .catch(err => console.error('Erro ao conectar:', err));


app.use(cors());
app.use(express.json());


app.get('/', (req, res) => {
  res.json({ message: 'API do Tour Cidades Gêmeas' });
});


app.get('/api/users', async (req, res) => {
  try {
    const result = await client.query(
      'SELECT Id, Nome, Email FROM Tour.Usuario'
    );
    res.json(result.rows);
  } catch (error) {
    console.error('Erro ao listar usuários:', error);
    res.status(500).json({ error: 'Erro ao listar usuários' });
  }
});

app.post('/api/users/register', async (req, res) => {
  try {
    const { nome, email, senha } = req.body;

    if (!nome || !email || !senha) {
      return res.status(400).json({ error: 'Todos os campos são obrigatórios' });
    }

    const userExists = await client.query(
      'SELECT * FROM Tour.Usuario WHERE Email = $1',
      [email]
    );

    if (userExists.rows.length > 0) {
      return res.status(400).json({ error: 'Email já cadastrado' });
    }

    const result = await client.query(
      `INSERT INTO Tour.Usuario (Nome, Email, Senha, Created_At, Updated_At) 
       VALUES ($1, $2, $3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP) 
       RETURNING Id, Nome, Email`,
      [nome, email, senha]
    );

    res.status(201).json(result.rows[0]);
  } catch (error) {
    console.error('Erro ao cadastrar usuário:', error);
    res.status(500).json({ error: 'Erro ao cadastrar usuário' });
  }
});

app.post('/api/users/login', async (req, res) => {
  try {
    const { email, senha } = req.body;

    const result = await client.query(
      'SELECT * FROM Tour.Usuario WHERE Email = $1 AND Senha = $2',
      [email, senha]
    );

    if (result.rows.length === 0) {
      return res.status(401).json({ error: 'Email ou senha inválidos' });
    }

    const user = result.rows[0];
    res.json({
      user: {
        id: user.id,
        nome: user.nome,
        email: user.email
      }
    });
  } catch (error) {
    console.error('Erro ao fazer login:', error);
    res.status(500).json({ error: 'Erro ao fazer login' });
  }
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, () => {
  console.log(`Servidor rodando na porta ${PORT}`);
});
