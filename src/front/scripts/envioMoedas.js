document.getElementById("envioMoedasForm").addEventListener("submit", function(event) {
    event.preventDefault();
    
    const alunoId = document.getElementById("aluno").value;
    const quantidade = parseInt(document.getElementById("quantidade").value, 10);
    const mensagem = document.getElementById("mensagem").value;
  
    const data = {
      alunoId,
      quantidade,
      mensagem
    };
  
    fetch("http://localhost:8080/api/enviarMoedas", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(data)
    })
    .then(response => {
      if (!response.ok) throw new Error('Erro ao enviar moedas');
      alert("Moedas enviadas com sucesso!");
    })
    .catch(error => {
      console.error('Erro:', error);
    });
  });
  