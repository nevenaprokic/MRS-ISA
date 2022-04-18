import {Link} from 'react-router-dom';
import classes from './MainNavigation.module.css';
import React from 'react';
import logo from '../../logo.png';


function MainNavigationHome(){
    return <header className={classes.header}>
        <div><img src={logo} /></div>
        <div className={classes.logo}></div>
    </header>
}

export default MainNavigationHome;