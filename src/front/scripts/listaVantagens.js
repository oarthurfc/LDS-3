document.addEventListener("DOMContentLoaded", async () => {
    const vantagensUl = document.getElementById("vantagensUl");
    const modal = document.getElementById("modal");
    const confirmButton = document.getElementById("confirmButton");
    const alunoIdInput = document.getElementById('alunoId'); 
    let currentVantagemId; 

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

                
                const img = document.createElement("img");
                img.src = vantagem.foto || 'default-image-url.jpg'; 

                
                const infoDiv = document.createElement("div");
                infoDiv.className = "vantagem-info";

                
                const descricao = document.createElement("div");
                descricao.innerText = vantagem.descricao;

                
                const custo = document.createElement("div");
                custo.innerText = `Custo: ${vantagem.custo} moedas`;

               
                const empresa = document.createElement("div");
                empresa.innerText = `Empresa: ${vantagem.empresa.nome}`;

             
                infoDiv.appendChild(descricao);
                infoDiv.appendChild(custo);
                infoDiv.appendChild(empresa);

                li.appendChild(img);
                li.appendChild(infoDiv);

             
                const button = document.createElement("button");
                button.innerText = "Resgatar Vantagem";
                button.onclick = () => openModal(vantagem.id); 
                li.appendChild(button);

                vantagensUl.appendChild(li);
            });
        } catch (error) {
            console.error("Erro ao carregar vantagens:", error);
        }
    }

    function openModal(vantagemId) {
        currentVantagemId = vantagemId; 
        modal.style.display = "block"; 
    }

    function closeModal() {
        modal.style.display = "none"; 
        alunoIdInput.value = ''; 
    }

    confirmButton.onclick = async () => {
        const alunoId = alunoIdInput.value; 
    
        if (!alunoId) {
            alert('ID do aluno não pode ser vazio.');
            return;
        }
    
        console.log(`Resgatando vantagem: Aluno ID = ${alunoId}, Vantagem ID = ${currentVantagemId}`); // Log para depuração
    
        try {
           
            const alunoResponse = await fetch(`http://localhost:8080/api/alunos/${alunoId}`);
            if (!alunoResponse.ok) {
                const errorText = await alunoResponse.text(); 
                throw new Error(`Aluno não encontrado: ${errorText}`);
            }
    
           
            const response = await fetch(`http://localhost:8080/api/alunos/${alunoId}/resgatar-vantagem/${currentVantagemId}`, {
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
                const errorText = await response.text(); 
                alert(`Erro ao resgatar vantagem: ${errorText}`); 
            }
        } catch (error) {
            alert(`Erro ao resgatar vantagem: ${error.message}`); 
        }
    };
    


    window.onclick = function(event) {
        if (event.target === modal) {
            closeModal();
        }
    };

   
    const closeButton = document.querySelector('.close');
    closeButton.onclick = closeModal;

    await carregarVantagens(); 
});
