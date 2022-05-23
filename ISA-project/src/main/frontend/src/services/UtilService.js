

export function compareString(sortAsc, first, second){
    return (sortAsc) ?  ((first > second) ? 1 : ((second > first) ? -1 : 0)) : ((first < second) ? 1 : ((second < first) ? -1 : 0))
  }

export function addDaysToDate(date_string, days){
  var date = new Date(date_string);
  let next_date = new Date(date.setDate(date.getDate() + parseInt(days)));
  return next_date;
}
export function addDays(date, days) {
  date.setDate(date. getDate() + parseInt(days));
  return date;
  }

export function arrayToDateString(arr){
    let d = new Date();
    d.setFullYear(arr[0]);
    d.setMonth(arr[1]-1);
    d.setDate(arr[2]);
    return d;
}