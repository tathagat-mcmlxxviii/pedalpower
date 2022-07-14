"use strict";

// tag::vars[]
const React = require("react"); // <1>
const ReactDOM = require("react-dom"); // <2>
// end::vars[]
import { startTrip, userEmail, sendDataPoint } from "./api/api.js";

import Button from "@mui/material/Button";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import Container from "@mui/material/Container";
import Typography from "@mui/material/Typography";

import SimpleBottomNavigation from "./components/bottomnavigation.js";
import ResponsiveAppBar from "./components/appbar.js";
import CircularWithValueLabel from "./components/trackbar.js";

let dataPointTimer;

// tag::app[]
class App extends React.Component {
  // <1>

  constructor(props) {
    super(props);
    this.state = { distance: 0 };
  }

  componentDidMount() {
    // <2>
  }

  render() {
    // <3>
    return (
      <React.Fragment>
        <Box
          sx={{
            display: "flex",
            flexDirection: "column",
            minHeight: "100vh",
          }}
        >
          <ResponsiveAppBar />
          <Box sx={{ flexGrow: 1 }}>
            <Grid container spacing={2}>
              <Grid item xs={12}></Grid>
              <Grid item xs={5}></Grid>
              <Grid item xs={2}>
                <Box
                  sx={{
                    px: "32%",
                  }}
                >
                  <CircularWithValueLabel value={this.state.distance} />
                </Box>
              </Grid>
              <Grid item xs={5}></Grid>
              <Grid item xs={4}></Grid>
              <Grid item xs={4}>
                <Box
                  sx={{
                    display: "flex",
                    justifyContent: "center",
                  }}
                >
                  <Button
                    variant="contained"
                    onClick={() => {
                      this.handleStart();
                    }}
                  >
                    Start
                  </Button>
                </Box>
              </Grid>
              <Grid item xs={4}></Grid>
            </Grid>
          </Box>
          <Box
            component="footer"
            sx={{
              py: 2,
              px: 2,
              mt: "auto",
              backgroundColor: (theme) => theme.palette.grey[200],
            }}
          >
            <Container maxWidth="sm">
              <SimpleBottomNavigation />
            </Container>
          </Box>
        </Box>
      </React.Fragment>
    );
  }

  handleStart() {
    startTrip(userEmail).then((data) => {
      if (data.body.length) {
        const trip = JSON.parse(data.body);
        this.setState({ distance: trip.km });
      }
      this.startUpdateLoop();
    });
  }

  startUpdateLoop() {
    dataPointTimer = setInterval(() => {
      sendDataPoint().then((data) => {
        console.log(data);
        if (data && data.body.length) {
          const trip = JSON.parse(data.body);
          this.setState({ direction: trip.km });
        }
      });
    }, 2000);
  }

  stopUpdateLoop() {
    clearInterval(dataPointTimer);
  }
}
// end::app[]

// tag::render[]
ReactDOM.render(<App />, document.getElementById("react"));
// end::render[]
