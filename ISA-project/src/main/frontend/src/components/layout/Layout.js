import MainNavigation from './MainNavigation';
import classes from './Layout.module.css';

function Layout(props) {
  return (
    <div style={{backgroundColor: "white"}}>
       <MainNavigation />
       <main className={classes.main}>{props.children}</main>
    </div>
  );
}

export default Layout;