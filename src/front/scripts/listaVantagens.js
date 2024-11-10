document.addEventListener("DOMContentLoaded", async () => {
    const vantagensUl = document.getElementById("vantagensUl");

    
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
                img.alt = vantagem.descricao; 
                img.style.width = '100px'; 
                img.style.height = 'auto'; 
                img.style.marginRight = '10px'; 

            
                li.appendChild(img);
                li.appendChild(document.createTextNode(`- Custo: ${vantagem.custo} moedas - Empresa: ${vantagem.empresa.nome}`));
                vantagensUl.appendChild(li);
            });
        } catch (error) {
            console.error("Erro ao carregar vantagens:", error);
        }
    }


    await carregarVantagens();
}); 