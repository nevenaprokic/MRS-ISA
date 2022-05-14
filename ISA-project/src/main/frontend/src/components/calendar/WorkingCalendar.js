import FullCalendar from '@fullcalendar/react'
import dayGridPlugin from '@fullcalendar/daygrid'
import "./WorkingCalendar.scss";
import Grid from '@mui/material/Grid';
import interactionPlugin from "@fullcalendar/interaction"; // needed for dayClick
import CalendarSidebar from './CalendarSidebar';
import timeGridPlugin from '@fullcalendar/timegrid'
import { INITIAL_EVENTS, createEventId } from './event-utils'
import { useEffect, useState } from 'react';


export default function WorkingCalendar(){


  const [state, setState] = useState();

  useEffect(() => {
    // async function setData() {
    //   const offersData = await getOffers[type]();
    //   setOffers(offersData ? offersData.data : {});
    //   if(setLastSearchedOffers)  
    //     setLastSearchedOffers(offersData ? offersData.data : {});

    // return offersData;    
    // }
    // setData();
    let s = {
      weekendsVisible: true,
      currentEvents: []
    }
    setState(s);
}, [])

  const handleDateSelect = (selectInfo) => {
    let viewType = selectInfo.view.type;
    if(viewType == "timeGridWeek" || viewType == "timeGridDay"){
      let calendarApi = selectInfo.view.calendar
      calendarApi.unselect()
      alert("Select option is available onlly on monty view");

    }
    else{
      let title = prompt('Please enter a new title for your event')
      let calendarApi = selectInfo.view.calendar

      calendarApi.unselect() // clear date selection
    
      if (title) {
        calendarApi.addEvent({
          id: createEventId(),
          title,
          start: selectInfo.startStr,
          end: selectInfo.endStr,
          allDay: selectInfo.allDay
        })
      }
    }
  }


  const handleEventClick = (clickInfo) => {
    if (true) {//dodati popup za da li ste sigurni
      console.log("aaaa",clickInfo);//currentViewType
      alert(clickInfo);
    }
  }

  const handleEvents = (events) => {
    setState({
      currentEvents: events
    })
  }
    

    return (
      <div className='demo-app'>
        <CalendarSidebar state={state}/>
        <div className='demo-app-main'>
          <FullCalendar
            plugins={[dayGridPlugin, timeGridPlugin, interactionPlugin]}
            headerToolbar={{
              left: 'prev,next',
              center: 'title',
              right: 'dayGridMonth,timeGridWeek,timeGridDay',
              
            }}
            initialView='dayGridMonth'
            editable={false}
            selectable={true}
            selectMirror={true}
            dayMaxEvents={true}
            weekends={true}
            
            //eventBackgroundColor={ "#CC7351"}
            
            initialEvents={INITIAL_EVENTS} // alternatively, use the `events` setting to fetch from a feed
            select={handleDateSelect}
            eventContent={renderEventContent} // custom render function
            eventClick={handleEventClick}
            eventsSet={handleEvents} // called after events are initialized/added/changed/removed
            /* you can update a remote database when these fire:
            eventAdd={function(){}}
            eventChange={function(){}}
            eventRemove={function(){}}
            */

          />
          
        </div>
      </div>
    )
  }


function renderEventContent(eventInfo) {
  return (
    <>
      <b>{eventInfo.timeText}</b>
      <i>{eventInfo.event.title}</i>
    </>
  )
}


