function buscarExtrato() {
  const userId = document.getElementById("userId").value;

  if (!userId) {
    alert("Por favor, insira um ID de usuário.");
    return;
  }

  fetch(`http://localhost:8080/api/extrato/${userId}`)
    .then(response => {
      if (!response.ok) {
        throw new Error("Erro ao buscar o extrato");
      }
      return response.json();
    })
    .then(data => {
      const extratoList = document.getElementById("extratoList");
      extratoList.innerHTML = ""; 

      if (data.length === 0) {
        extratoList.innerHTML = "<li>Nenhuma transação encontrada.</li>";
      } else {
        data.forEach(transacao => {
          const listItem = document.createElement("li");
          listItem.textContent = `Data: ${transacao.data} - Tipo: ${transacao.tipo} - Quantidade: ${transacao.quantidade} - Mensagem: ${transacao.mensagem}`;
          extratoList.appendChild(listItem);
        });
      }
    })
    .catch(error => console.error('Erro ao carregar extrato:', error));
}
