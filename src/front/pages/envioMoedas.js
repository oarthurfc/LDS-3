document.addEventListener("DOMContentLoaded", () => {
  const alunoSelect = document.getElementById("aluno");


  async function carregarAlunos() {
      try {
          const response = await fetch("http://localhost:8080/api/alunos");
          if (!response.ok) throw new Error(`Erro ao carregar lista de alunos: ${response.statusText}`);

          const alunos = await response.json();
          alunos.forEach(aluno => {
              const option = document.createElement("option");
              option.value = aluno.id;
              option.textContent = aluno.nome;
              alunoSelect.appendChild(option);
          });
      } catch (error) {
          console.error("Erro ao carregar alunos:", error);
          alert("Erro ao carregar lista de alunos. Por favor, verifique a conexão.");
      }
  }

  
  carregarAlunos();


  document.getElementById("envioMoedasForm").addEventListener("submit", async (event) => {
      event.preventDefault();

      const alunoId = alunoSelect.value;
      const quantidade = document.getElementById("quantidade").value;
      const mensagem = document.getElementById("mensagem").value;

      const data = {
          alunoId,
          quantidade,
          mensagem,
          professorId: null
      };

      try {
          const response = await fetch("http://localhost:8080/api/transacoes", {
              method: "POST",
              headers: {
                  "Content-Type": "application/json"
              },
              body: JSON.stringify(data)
          });

          if (!response.ok) throw new Error(`Erro ao enviar moedas: ${response.statusText}`);

          alert("Moedas enviadas com sucesso!");
      } catch (error) {
          console.error("Erro ao enviar moedas:", error);
          alert("Erro ao enviar moedas. Verifique os dados e tente novamente.");
      }
  });
});
