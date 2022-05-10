

export function compareString(sortAsc, first, second){
    return (sortAsc) ?  ((first > second) ? 1 : ((second > first) ? -1 : 0)) : ((first < second) ? 1 : ((second < first) ? -1 : 0))
  }