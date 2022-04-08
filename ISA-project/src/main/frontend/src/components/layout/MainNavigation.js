import {Link} from 'react-router-dom';
import classes from './MainNavigation.module.css';
import React from 'react';
import logo from '../../logo.png';


function MainNavigation(){
    return <header className={classes.header}>
        <div><img src={logo} /></div>
        <div className={classes.logo}></div>
        <nav>
            <ul>
                <li><Link to='/registration'>Sign up</Link></li>
                <li><Link to='/log-in'>Log in</Link></li> 
            </ul>
        </nav>
    </header>
}

export default MainNavigation;