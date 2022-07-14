export const userEmail = "test@mail.com";

export const mockedGpsData = [
  "51.764101,19.412424",
  "51.765024,19.414967",
  "51.766604,19.419462",
  "51.766730,19.421790",
  "51.765150,19.422799",
  "51.762361,19.424687",
  "51.760283,19.426265",
  "51.759426,19.427670",
  "51.757015,19.429440",
  "51.755966,19.426747",
  "51.755999,19.423035",
  "51.756876,19.418840",
  "51.758138,19.414194",
  "51.759586,19.411222",
  "51.761791,19.409023",
  "51.762608,19.408251",
  "51.763372,19.410450",
  "51.764116,19.412456",
];

export function startTrip(email) {
  return fetch("http://localhost:8080/trip/start?email=" + email).then((response) => response.json());
}

export function sendDataPoint(email) {
  if (mockedGpsData.length > 0) {
    return fetch("http://localhost:8080/trip", {
      method: "put",
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        email: userEmail,
        dataPoint: mockedGpsData.pop(),
      }),
    }).then((response) => response.json());
  }
}
