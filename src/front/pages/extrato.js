async function buscarExtrato() {
    const userId = document.getElementById("userId").value;

    if (!userId) {
        alert("Por favor, insira o ID do usuário.");
        return;
    }

    try {
        const response = await fetch(`/api/alunos/${userId}`);

        if (!response.ok) {
            const errorText = await response.text();
            console.error("Erro ao buscar extrato:", errorText);
            alert("Erro ao buscar o extrato. Verifique o ID e tente novamente.");
            return;
        }


        const aluno = await response.json();

        const extratoList = document.getElementById("extratoList");
        extratoList.innerHTML = "";

    
        if (!aluno.transacoes || aluno.transacoes.length === 0) {
            const listItem = document.createElement("li");
            listItem.textContent = "Nenhuma transação encontrada.";
            extratoList.appendChild(listItem);
            return;
        }

        aluno.transacoes.forEach(transacao => {
            const listItem = document.createElement("li");
            listItem.textContent = `Data: ${new Date(transacao.data).toLocaleDateString()} - Tipo: ${transacao.tipo} - Montante: ${transacao.montante}`;
            extratoList.appendChild(listItem);
        });
    } catch (error) {
        console.error("Erro:", error);
        alert("Ocorreu um erro ao buscar o extrato.");
    }
}
