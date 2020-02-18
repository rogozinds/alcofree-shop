import React from 'react';
import { connect } from "react-redux";
import Grid from '@material-ui/core/Grid';
import Select from '@material-ui/core/Select';
import MenuItem from '@material-ui/core/MenuItem';
import SimpleCard from './SimpleCard';
import {getProducts} from './list.reducer';

class Home extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            countries : [],
            allProducts:[],
            products: [],
            countryFilter:"All"
        };
    }

  componentDidMount() {
    this.props.getProducts();
  }

  filterByCountry(e) {
    let products;
    if(e.value==="All") {
         this.setState({products:this.state.allProducts});
    }
    else if(e.value!=""){
        products = this.state.allProducts.filter(p=>p.shop.country==e.value);
        this.setState({products:products});
    }
    this.setState({countryFilter:e.value});
  }

  render() {
  return (
    //input form
    <div className="App">
      <Select
          value={this.state.countryFilter}
          onChange={(e)=>{this.filterByCountry(e.target)}}
          inputProps={{
            name: 'country',
            id: "country-filter"
          }}
        >
      </Select>

      {
      <Grid container justify="center" spacing={2}>
        { this.props.products.map(item =>
              <Grid key={item.id} item lg={3} sm={6}>
              <SimpleCard name={item.name}
                          description={item.description}
                          img_url={item.img_url}
                          country={"MOCK COUNTRY"}>
              </SimpleCard>
              </Grid>
          )}
      </Grid>
      }
    </div>

  );
  }
}

const mapStateToProps = (state) => {
    return {products: state.list.products}
  };

const mapDispatchToProps = {getProducts};

export default connect(mapStateToProps, mapDispatchToProps)(Home);