document.addEventListener('DOMContentLoaded', () => {
  chrome.storage.local.get({sites: []}, (result) => {
    let sites = result.sites;
    let listElement = document.getElementById('siteList');
    sites.forEach((site) => {
      let li = document.createElement('li');
      let a = document.createElement('a');
      a.href = site;
      a.textContent = site;
      a.target = '_blank'; // Ouvre dans un nouvel onglet
      li.appendChild(a);
      listElement.appendChild(li);
    });
  });
  // Ajoutez un gestionnaire d'événements pour le bouton de suppression
  document.getElementById('clearData').addEventListener('click', () => {
    chrome.storage.local.set({sites: []}, () => {
      // Mettez à jour l'affichage ou informez l'utilisateur que les données ont été supprimées
      document.getElementById('siteList').innerHTML = ''; // Vide la liste
      // Optionnel : Affichez un message confirmant la suppression
      alert('Historique supprime.');
    });
  });
});
