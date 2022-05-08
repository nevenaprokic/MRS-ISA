import {Link} from 'react-router-dom';
import classes from './MainNavigation.module.css';
import React from 'react';
import logo from '../../logo.png';
import { getRoleFromToken } from '../../app/jwtTokenUtils';


function MainNavigationHome(){
    return <header className={classes.header}>
        <div><img src={logo} /></div>
        <div className={classes.logo}></div>
        <div className='headerLine'></div> 
        {!!getRoleFromToken() && <Link to='/'>Log out</Link> }
    </header>
}

export default MainNavigationHome;