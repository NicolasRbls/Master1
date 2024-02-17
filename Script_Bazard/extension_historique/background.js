chrome.tabs.onUpdated.addListener((tabId, changeInfo, tab) => {
  if (changeInfo.url) {
    console.log(`URL changée : ${changeInfo.url}`);
    chrome.storage.local.get({sites: []}, (result) => {
      let sites = result.sites;
      sites.push(changeInfo.url);
      chrome.storage.local.set({sites});
    });
  }
});

// Fonction pour supprimer les données
function clearSites() {
  chrome.storage.local.set({sites: []});
}

// Vérifie périodiquement si 24 heures se sont écoulées pour supprimer les données
setInterval(() => {
  clearSites(); // Supprime les données
}, 24 * 60 * 60 * 1000); // 24 heures en millisecondes
