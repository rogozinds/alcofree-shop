import axios from 'axios';

import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

export const  ACTION_TYPES = {
  FETCH_LIST: 'list/FETCH_LIST',
  FETCHING_LIST: 'list/FETCHING_LIST',
  ADD: 'list/ADD'
};

const initialState = {
    products:[]
};
// Actions
const apiUrl = 'api/products';

const fetchDate = async function(requestUrl) {
    console.log("!!!!!!!!!!!!!!!!!!!!!The fetch is callled");
    return axios.get(requestUrl)
}
export const getProducts = () => async dispatch => {
  const requestUrl = apiUrl;
  let payload = await fetchDate(requestUrl);
  console.log(payload.data);
  dispatch({
        type: ACTION_TYPES.FETCH_LIST,
        payload: payload.data
  });
};
// Reducer
export default (state = initialState, action) => {
  switch (action.type) {
    case ACTION_TYPES.FETCHING_LIST:

    case ACTION_TYPES.FETCH_LIST:
      console.log("PPPP", action.payload);
      return {
        products:action.payload
      };
    case ACTION_TYPES.ADD:
      return {
        ...state
      };
    default:
      return state;
  }
};
