"use strict";

// tag::vars[]
const React = require("react"); // <1>
const ReactDOM = require("react-dom"); // <2>
// end::vars[]
import {
  startTrip,
  userEmail,
  sendDataPoint,
  endTrip,
  getUser,
} from "./api/api.js";

import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import Container from "@mui/material/Container";

import SimpleBottomNavigation from "./components/bottomnavigation.js";
import ResponsiveAppBar from "./components/appbar.js";

import HomeSection from "./components/home.js";
import TicketSection from "./components/ticket.js";

// tag::app[]
class App extends React.Component {
  // <1>

  constructor(props) {
    super(props);
    this.state = {
      distance: 0,
      points: 0,
      page: "home",
    };
  }

  componentDidMount() {
    // <2>
    getUser(userEmail).then((user) => {
      this.updateState(this.state.distance, user.points, this.state.page);
    });
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

          {this.getContentElement()}

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
              <SimpleBottomNavigation
                onPageChange={this.changePage.bind(this)}
              />
            </Container>
          </Box>
        </Box>
      </React.Fragment>
    );
  }

  changePage(page) {
    this.updateState(this.state.distance, this.state.points, page);
  }

  getContentElement() {
    if (this.state.page == "home") {
      return <HomeSection state={this.state} />;
    } else if (this.state.page == "ticket") {
      return <TicketSection state={this.state} />;
    } else {
      return <Box />;
    }
  }

  updateState(distance, points, page) {
    this.setState({
      distance: distance,
      points: points,
      page: page,
    });
  }
}
// end::app[]

// tag::render[]
ReactDOM.render(<App />, document.getElementById("react"));
// end::render[]
