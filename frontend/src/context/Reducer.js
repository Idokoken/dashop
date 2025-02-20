import {
    FETCH_DATA_FAILURE, FETCH_DATA_REQUEST, FETCH_DATA_SUCCESS, PRODUCT_DATA_FAILURE, PRODUCT_DATA_REQUEST,
    PRODUCT_DATA_SUCCESS
} from './Action'


const Reducer = (state, action) => {
    const { type, payload } = action

    switch (type) {
        case FETCH_DATA_REQUEST:
            return { ...state, loading: true };
        case FETCH_DATA_SUCCESS:
            console.log('getting PRODUCT', payload)
            return { ...state, loading: false, products: payload, error: null };
        case FETCH_DATA_FAILURE:
            return { ...state, loading: false, error: payload };

        case PRODUCT_DATA_REQUEST:
            return { ...state, loading: true };
        case PRODUCT_DATA_SUCCESS:
            console.log('getting PRODUCT', payload)
            return { ...state, loading: false, products: [...state.products, payload], error: null };


        default:
            throw new Error(`No such action ${type}`)
        // return state
    }
}

export default Reducer;