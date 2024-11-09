window.onload = function() {
    fetch("http://localhost:8080/api/vantagens")
      .then(response => response.json())
      .then(data => {
        const container = document.getElementById("vantagensContainer");
        
        if (data.length === 0) {
          container.innerHTML = "<p>Nenhuma vantagem disponível no momento.</p>";
        } else {
          data.forEach(vantagem => {
            const vantagemDiv = document.createElement("div");
            vantagemDiv.classList.add("vantagem-item");
            
            vantagemDiv.innerHTML = `
              <h3>${vantagem.nome}</h3>
              <p>${vantagem.descricao}</p>
              <p><strong>Custo:</strong> ${vantagem.custo} moedas</p>
              <img src="${vantagem.imagemUrl}" alt="${vantagem.nome}" class="vantagem-img">
            `;
            
            container.appendChild(vantagemDiv);
          });
        }
      })
      .catch(error => console.error('Erro ao carregar vantagens:', error));
  };
  