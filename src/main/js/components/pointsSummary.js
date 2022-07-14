import * as React from "react";
import Box from "@mui/material/Box";
import { Typography } from "@mui/material";

export default function PointsSummary(props) {
  return (
    <Typography variant="h6" component="div">
      Your points: {props.points}
    </Typography>
  );
}
