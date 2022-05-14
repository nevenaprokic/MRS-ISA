
let eventGuid = 0
let todayStr = new Date().toISOString().replace(/T.*$/, '') // YYYY-MM-DD of today

export const INITIAL_EVENTS = [
  {
    id: createEventId(),
    title: 'All-day event',
    start: "2022-05-14",
    end: "2022-05-20",
    rendering: 'background',
    reservation: false,
  },

  {
    id: createEventId(),
    title: 'All-day event',
    start: "2022-05-24",
    end: "2022-05-27",
    rendering: 'background',
    reservation: true,
  },
]

export function createEventId() {
  return String(eventGuid++)
}
