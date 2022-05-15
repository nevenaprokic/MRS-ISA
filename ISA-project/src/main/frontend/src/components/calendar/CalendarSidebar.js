
import { formatDate } from '@fullcalendar/react';
import TextField from '@mui/material/TextField';
import Autocomplete from '@mui/material/Autocomplete';
import Typography from '@mui/material/Typography';
import { getCalendarEvents } from '../../services/CalendarService';

export default function CalendarSidebar({state, offers}) {

  console.log(state)
    const handleWeekendsToggle = () => {
        this.setState({
          weekendsVisible: !this.state.weekendsVisible
        })
      }

    function renderSidebarEvent(event) {
        return (
          <li key={event.id}>
            <b>{formatDate(event.start, {year: 'numeric', month: 'short', day: 'numeric'})}</b>
            <label> - </label>
            <b>{formatDate(event.end, {year: 'numeric', month: 'short', day: 'numeric'})}</b>
            <i>{event.title}</i>
          </li>
        )
      }

    const handlerOfferChange = (event, selectedOffer) =>{
        getCalendarEvents(selectedOffer.id);
    }

  
    return (
      !!state &&
      <div className='demo-app-sidebar'>
        <div className='demo-app-sidebar-section'>
          <Typography variant='body1'>
            Select the offer for which you want to see the calendar 
          </Typography>
          <br/>
          <Autocomplete
              disablePortal
              id="combo-box-demo"
              options={offers}
              sx={{ width: 250 }}
              onChange={(event, newValue) => {handlerOfferChange(event, newValue)}}
              renderInput={(params) => <TextField {...params} label="Offer" />}
            />
          <br/>
          <h2>Instructions</h2>
          <ul>
            <li>Select dates and you will be abled to definebe able to define unavailable dates for the selected offer </li>
            <li>Click an event to see the details</li>
            
          </ul>
        </div>
        <div className='demo-app-sidebar-section'>
          <h2>All Events ({state.currentEvents.length})</h2>
          <ul>
            {state.currentEvents.map(renderSidebarEvent)}
          </ul>
        </div>
      </div>
    )
  }