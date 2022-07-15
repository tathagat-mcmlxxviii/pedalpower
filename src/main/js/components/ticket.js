import * as React from "react";
import { userEmail, buy, getUser } from "./../api/api.js";

import { Grid, Box } from "@mui/material";

import PointsSummary from "./pointsSummary.js";
import BuyList from "./buyList.js";

class TicketSection extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      points: props.state.points,
      ticket: "",
    };
  }

  componentDidMount() {
    // <2>
    getUser(userEmail).then((user) => {
      this.setState({
        points: user.points,
        ticket: this.state.ticket,
      });
    });
  }

  render() {
    console.log(this.state);
    return (
      <Box sx={{ flexGrow: 1 }}>
        <Grid container spacing={2}>
          <Grid item xs={4}></Grid>
          <Grid item xs={4}>
            <Box
              sx={{
                display: "flex",
                justifyContent: "center",
              }}
            >
              <PointsSummary points={this.state.points} />
            </Box>
          </Grid>
          <Grid item xs={4}></Grid>
          <Grid item xs={5}></Grid>
          <Grid item xs={2}>
            <Box
              sx={{
                display: "flex",
                justifyContent: "center",
              }}
            >
              <BuyList onClick={this.handleBuy.bind(this)} />
            </Box>
          </Grid>
          <Grid item xs={5}></Grid>
          <Grid item xs={2}></Grid>
          <Grid item xs={6}>
            <Box
              sx={{
                display: "flex",
                justifyContent: "center",
              }}
            >
              <img src={"data:image/png;base64, " + this.state.ticket} alt="Your ticket will be here"/>
            </Box>
          </Grid>
          <Grid item xs={2}></Grid>
        </Grid>
      </Box>
    );
  }

  handleBuy(buyUrl) {
    buy(userEmail, buyUrl).then((response) => {
      console.log(response);
      getUser(userEmail).then((user) => {
        this.setState({
          points: user.points,
          ticket: response.base64TicketImg,
        });
      });
    });
  }
}

export default TicketSection;
