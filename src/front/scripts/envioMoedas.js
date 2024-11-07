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


  async function enviarMoedas() {
      const professorId = document.getElementById("professorId").value; // ID do professor
      const alunoId = document.getElementById("aluno").value; // ID do aluno
      const montante = document.getElementById("quantidade").value; // Montante a ser enviado
      const motivo = document.getElementById("mensagem").value; // Mensagem do envio

      if (!professorId || !alunoId || !montante || !motivo) {
          alert("Por favor, preencha todos os campos.");
          return;
      }

      try {
          const response = await fetch(`http://localhost:8080/api/professores/${professorId}/enviar-moedas/${alunoId}?montante=${montante}&motivo=${motivo}`, {
              method: 'POST',
              headers: {
                  'Content-Type': 'application/json'
              }
          });

          if (!response.ok) {
              const errorText = await response.text();
              console.error("Erro ao enviar moedas:", errorText);
              alert("Erro ao enviar moedas. Verifique os dados e tente novamente.");
              return;
          }

          const result = await response.text(); // Recebe a resposta do servidor
          alert(result); // Exibe a mensagem de sucesso
      } catch (error) {
          console.error("Erro:", error);
          alert("Ocorreu um erro ao enviar as moedas.");
      }
  }

  document.getElementById("envioMoedasForm").addEventListener("submit", async (event) => {
      event.preventDefault();

      enviarMoedas();
  });
});
