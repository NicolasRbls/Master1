const express = require('express');
const redis = require('redis');
const { v4: uuidv4 } = require('uuid');

const app = express();
const port = 3000;

// Configuration Redis avec la nouvelle API
const client = redis.createClient();

client.on('connect', () => {
    console.log('Connecté à Redis');
});

client.on('error', (err) => {
    console.error('Erreur Redis :', err);
});

// Connexion Redis (nécessaire avec la nouvelle API)
client.connect();

app.use(express.json());

// Endpoint pour enregistrer les logs
app.post('/log', async (req, res) => {
    const { action, details, timestamp } = req.body;
    const logId = uuidv4(); // Générer un ID unique pour le log

    const logData = {
        action,
        details,
        timestamp
    };

    try {
        // Utiliser hSet avec la nouvelle API
        await client.hSet('logs', logId, JSON.stringify(logData));
        console.log(`Log enregistré : ${logId}`);
        res.status(200).json({ message: 'Log enregistré avec succès', logId });
    } catch (err) {
        console.error(err);
        res.status(500).json({ message: 'Erreur lors de l\'enregistrement du log' });
    }
});

// Servir les fichiers HTML statiques
app.use(express.static('public'));

// Lancer le serveur
app.listen(port, () => {
    console.log(`Serveur en cours d'exécution sur http://localhost:${port}`);
});


async function getOldestLog() {
    try {
        const logs = await client.hGetAll('logs'); // Récupérer toutes les logs
        let oldestLog = null;

        // Parcourir les logs pour trouver la plus ancienne
        for (const [logId, logData] of Object.entries(logs)) {
            const log = JSON.parse(logData); // Convertir la valeur JSON
            if (!oldestLog || new Date(log.timestamp) < new Date(oldestLog.timestamp)) {
                oldestLog = { logId, ...log }; // Mettre à jour si c'est plus ancien
            }
        }

        console.log('Log la plus ancienne :', oldestLog);
        return oldestLog;
    } catch (err) {
        console.error('Erreur lors de la récupération des logs :', err);
    }
}

// Appelle cette fonction
getOldestLog();
