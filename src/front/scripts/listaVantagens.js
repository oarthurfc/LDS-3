document.addEventListener("DOMContentLoaded", async () => {
    const vantagensUl = document.getElementById("vantagensUl");
    const modal = document.getElementById("modal");
    const confirmButton = document.getElementById("confirmButton");
    const alunoIdInput = document.getElementById('alunoId'); // Campo de entrada para o ID do aluno
    let currentVantagemId; // Variável para armazenar o ID da vantagem atual

    async function carregarVantagens() {
        try {
            const response = await fetch("http://localhost:8080/api/vantagens");
            if (!response.ok) {
                throw new Error("Erro ao carregar vantagens");
            }
            const vantagens = await response.json();

            vantagensUl.innerHTML = '';

            vantagens.forEach(vantagem => {
                const li = document.createElement("li");

                // Cria um elemento de imagem
                const img = document.createElement("img");
                img.src = vantagem.foto || 'default-image-url.jpg'; 

                // Cria um contêiner para as informações da vantagem
                const infoDiv = document.createElement("div");
                infoDiv.className = "vantagem-info";

                // Adiciona a descrição em uma linha
                const descricao = document.createElement("div");
                descricao.innerText = vantagem.descricao;

                // Adiciona o custo em uma linha
                const custo = document.createElement("div");
                custo.innerText = `Custo: ${vantagem.custo} moedas`;

                // Adiciona a empresa em uma linha
                const empresa = document.createElement("div");
                empresa.innerText = `Empresa: ${vantagem.empresa.nome}`;

                // Adiciona as informações ao contêiner
                infoDiv.appendChild(descricao);
                infoDiv.appendChild(custo);
                infoDiv.appendChild(empresa);

                li.appendChild(img);
                li.appendChild(infoDiv);

                // Botão para resgatar vantagem
                const button = document.createElement("button");
                button.innerText = "Resgatar Vantagem";
                button.onclick = () => openModal(vantagem.id); // Passa o ID da vantagem para a função openModal
                li.appendChild(button);

                vantagensUl.appendChild(li);
            });
        } catch (error) {
            console.error("Erro ao carregar vantagens:", error);
        }
    }

    function openModal(vantagemId) {
        currentVantagemId = vantagemId; // Armazena o ID da vantagem atual
        modal.style.display = "block"; // Exibe o modal
    }

    function closeModal() {
        modal.style.display = "none"; // Oculta o modal
        alunoIdInput.value = ''; // Limpa o campo de entrada do ID do aluno
    }

    confirmButton.onclick = async () => {
        const alunoId = alunoIdInput.value; // Obtém o ID do aluno do campo de entrada

        if (!alunoId) {
            alert('ID do aluno não pode ser vazio.');
            return;
        }

        console.log(`Resgatando vantagem: Aluno ID = ${alunoId}, Vantagem ID = ${currentVantagemId}`); // Log para depuração

        try {
            // Faz a requisição para resgatar a vantagem
            const response = await fetch(`/api/alunos/${alunoId}/resgatar-vantagem/${currentVantagemId}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
            });

            if (response.ok) {
                const result = await response.text();
                alert(`Vantagem resgatada com sucesso: ${result}`);
                closeModal();
            } else {
                const errorText = await response.text(); // Captura a mensagem de erro
                alert(`Erro ao resgatar vantagem: ${errorText}`); // Exibe a mensagem de erro
            }
        } catch (error) {
            alert(`Erro ao resgatar vantagem: ${error.message}`); // Exibe erro de rede
        }
    };

    // Fecha o modal se o usuário clicar fora dele
    window.onclick = function(event) {
        if (event.target === modal) {
            closeModal();
        }
    };

    // Adiciona evento de clique ao botão "X" para fechar o modal
    const closeButton = document.querySelector('.close');
    closeButton.onclick = closeModal;

    await carregarVantagens(); // Carrega as vantagens ao iniciar
});
