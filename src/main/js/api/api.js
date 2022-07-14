
export function startTrip(email){
    return fetch("http://localhost:8080/trip/start?email=" + email)
            .then(data => {
                const obj = JSON.parse(data)
            })
}