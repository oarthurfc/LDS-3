function initMultiStepForm() {
    const submitBtn = document.querySelector(".submit");

    submitBtn.addEventListener("click", function(event) {
        event.preventDefault();

        // Captura os valores dos campos no momento do clique
        const nome = document.getElementById("nome").value;
        
        const email = document.getElementById("email").value;
        const cnpj = document.getElementById("cnpj").value;
        const senha = document.getElementById("login-pass").value;
        // Obter a data atual
        const dataAtual = new Date();

        // Extrair o ano, mês e dia
        const ano = dataAtual.getFullYear();
        // O mês é retornado de 0 a 11, então precisamos adicionar 1 para obter o valor correto
        const mes = (dataAtual.getMonth() + 1).toString().padStart(2, '0'); // Adiciona zero à esquerda se for menor que 10
        const dia = dataAtual.getDate().toString().padStart(2, '0'); // Adiciona zero à esquerda se for menor que 10

        // Formatar a data no formato desejado "xxxx-yy-zz"
        const dataFormatada = `${ano}-${mes}-${dia}`;

        console.log(dataFormatada); // Saída: "2024-05-13"

        const data = {
            //id: 0, // id é autoincrementado
            nome,
            email,
            senha,
            cnpj,
            
        };
        
        console.log("data", data);

        fetch("http://localhost:8080/api/empresa", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            //return response.json();
        })
        .then(data => {
            console.log('Success:', data);
            // lidar com a resposta de sucesso aqui
            //window.location.href = "index.html";
        })
        .catch(error => {
            console.error('Error:', error);
            // lidar com o erro aqui
        })
    });
    //a
    
}

// async function fetchCadastro(tipo, email, senha) {
//     try {
//         let response = await fetch(`http://localhost:8080/locador`);

//         if (!response.ok) {
//             throw new Error(`HTTP error! status: ${response.status}`);
//         }

//         let data = await response.json();
//         console.log("galo:",data);
//         return data;
//     } catch (error) {
//         console.error("Erro ao buscar login:", error);
//     }
// }

// Chamada da função apenas uma vez quando a página é carregada
window.onload = function() {
    initMultiStepForm();
};

// Mask 

$(document).ready(function(){
    $(".input-cpf").mask("000.000.000-00");
});