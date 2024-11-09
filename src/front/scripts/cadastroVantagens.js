document.getElementById("cadastroVantagensForm").addEventListener("submit", function(event) {
    event.preventDefault();
    
    const nomeVantagem = document.getElementById("nomeVantagem").value;
    const descricao = document.getElementById("descricao").value;
    const custo = parseInt(document.getElementById("custo").value, 10);
    const imagemUrl = document.getElementById("imagemUrl").value;
  
    const data = {
      nome: nomeVantagem,
      descricao,
      custo,
      imagemUrl
    };
  
    fetch("http://localhost:8080/api/vantagens", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(data)
    })
    .then(response => {
      if (!response.ok) throw new Error('Erro ao cadastrar vantagem');
      alert("Vantagem cadastrada com sucesso!");
    })
    .catch(error => {
      console.error('Erro:', error);
    });
  });
  