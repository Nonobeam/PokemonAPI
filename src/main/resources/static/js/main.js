function getPokemonAtk() {
    const pokemon1Id = document.getElementById('pokemon1Id').value;
    const pokemon2Id = document.getElementById('pokemon2Id').value;

    fetch(`pokemon/info/${pokemon1Id}`)
        .then(response => response.json())
        .then(data => {
            const atkValue1 = data.atk;
            console.log(atkValue1);
            document.getElementById('atkValue1').innerHTML = `<p>Pokemon 1 ATK: ${atkValue1}</p>`;
        })
        .catch(error => console.error('Error fetching ATK value for Pokemon 1:', error));

    fetch(`pokemon/info/${pokemon2Id}`)
        .then(response => response.json())
        .then(data => {
            const atkValue2 = data.atk;
            console.log(atkValue2);
            document.getElementById('atkValue2').innerHTML += `<p>Pokemon 2 ATK: ${atkValue2}</p>`;
        })
        .catch(error => console.error('Error fetching ATK value for Pokemon 2:', error));
}