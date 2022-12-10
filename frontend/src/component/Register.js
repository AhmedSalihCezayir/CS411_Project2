import * as React from 'react';
import { styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';

export default function Register(props) {
  const [isSignup, setIsSignup] = React.useState(false);
  
  return (
      <div>
        <form>
          <Box display="flex" flexDirection={"column"} alignItems="center" justifyContent={"center"} margin="auto" marginTop={10}>
            <Typography variant="h4"> {isSignup ? "Sign Up": "Login"} </Typography>
            {isSignup && 
              <TextField margin="normal" id="name" label="Name" variant="standard" name="name" required/>
            }
              <TextField margin="normal" id="email" label="Email" type="email" variant="standard" name="email" required/>
              <TextField margin="normal" id="password" label="Password" variant="standard" type="password" name="password" required/>
              {isSignup ? (<Button sx={{marginTop:4}} variant="contained" onClick={props.register}> Sign Up </Button> ) :(<Button sx={{marginTop:4}} variant="contained"> Login </Button>)}
            <Button sx={{marginTop:4}} variant="outlined" onClick={()=>setIsSignup(!isSignup)}> To {isSignup ? "Login": "Sign Up"} </Button>
          </Box>
        </form>
      </div>
  )
}