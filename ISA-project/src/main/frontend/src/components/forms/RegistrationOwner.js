import * as React from 'react';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import Card from '../layout/Card';
import { NativeSelect, InputLabel, FormControl } from '@mui/material';
import { useForm } from "react-hook-form";

const theme = createTheme();
export default function RegistrationOwner() {
    const { register, handleSubmit, formState: { errors } } = useForm();
    const onSubmit = (data) => {
        console.log(data);
      }

    return (<Card>
      <ThemeProvider theme={theme}>
        <Container component="main" maxWidth="xs">
          <CssBaseline />
          <Box
            sx={{
              marginTop: 8,
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
            }}
          >
              <div><br/></div>
            <Typography component="h1" variant="h5">
              Sign up
            </Typography>
            <Box component="form" noValidate onSubmit={handleSubmit(onSubmit)} sx={{ mt: 3 }}>
              <Grid container spacing={2}>
                <Grid item xs={12} sm={6}>
                  <TextField
                    autoComplete="given-name"
                    name="firstName"
                    required
                    fullWidth
                    id="firstName"
                    label="First Name"
                    autoFocus
                    variant="standard"
                    {...register("firstName")}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    required
                    fullWidth
                    id="lastName"
                    label="Last Name"
                    name="lastName"
                    autoComplete="family-name"
                    variant="standard"
                    {...register("lastName")}
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    id="email"
                    label="Email Address"
                    name="email"
                    autoComplete="email"
                    variant="standard"
                    type="email"
                    {...register("email", )} />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    name="password"
                    label="Password"
                    type="password"
                    id="password"
                    autoComplete="new-password"
                    variant="standard"
                    {...register("password")}
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    name="confirmPassword"
                    label="Confirm password"
                    type="password"
                    id="confirmPassword"
                    autoComplete="new-password"
                    variant="standard"
                    {...register("confirmPassword")}
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    name="phoneNumber"
                    label="Phone number"
                    type="tel"
                    id="phoneNumber"
                    autoComplete="phone-number"
                    pattern="[0-9]{3}-[0-9]{2}-[0-9]{3}"
                    variant="standard"
                    {...register("phoneNumber")}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    autoComplete="street"
                    name="street"
                    required
                    fullWidth
                    id="street"
                    label="Street"
                    variant="standard"
                    {...register("street")}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    required
                    fullWidth
                    id="city"
                    label="City"
                    name="city"
                    autoComplete="city"
                    variant="standard"
                    {...register("city")}
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    required
                    fullWidth
                    name="state"
                    label="State"
                    id="state"
                    autoComplete="state"
                    variant="standard"
                    {...register("state")}
                  />
                </Grid>
                <Grid item xs={12}>
                    <FormControl fullWidth>
                    <InputLabel variant="standard" htmlFor="type">
                        Type of owner
                    </InputLabel>
                    <NativeSelect
                        defaultValue={30}
                        {...register("type")}
                        inputProps={{
                        name: 'type',
                        id: 'type',
                        }
                        }
                    >
                        <option value={10}>Instructor</option>
                        <option value={20}>Cottage owner</option>
                        <option value={30}>Ship owner</option>
                    </NativeSelect>
                    </FormControl>
                    <div><br/></div>
                    <Grid item xs={12}>
                        <TextField
                         required
                         fullWidth
                         name="explanation"
                         autoComplete="explanation"
                        id="explanation"
                        label="Multiline"
                        multiline
                        rows={4}
                        defaultValue="Explanation of registration"
                        variant="standard"
                        {...register("explanation")}
                        />
                </Grid>
                    
                </Grid>
              </Grid>
              <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
              >
                Sign Up
              </Button>
              <Grid container justifyContent="flex-end">
                <Grid item>
                  <Link href="/log-in" variant="body2">
                    Already have an account? Sign in
                  </Link>
                </Grid>
              </Grid>
              <div><br/></div>
            </Box>
          </Box>
        </Container>
      </ThemeProvider></Card>
    );
  }