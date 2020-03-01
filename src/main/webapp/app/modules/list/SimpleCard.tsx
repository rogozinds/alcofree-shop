import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardHeader from '@material-ui/core/CardHeader';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import classNames from 'classnames';
import  './SimpleCard.css';

class SimpleCard extends React.Component {

    constructor(props) {
        super(props);
        console.log("SimleCard props", props);
    }
  renderSpan() {
    return   <span>•</span>;
  }

  render() {

  return (
      <Card className="card" >
          <CardHeader style={{padding:"8px", width:"100%"}}title={this.props.name}/>
          <div style={{display: 'flex'}}>
              <CardContent className="content">
                  <Typography variant="body2" color="textSecondary" component="p">
                      {this.props.description}
                  </Typography>
                  <div>
                      {this.props.shop}
                  </div>
              </CardContent>
              <CardMedia className="media" component="div" image={this.props.img_url}/>
          </div>
      </Card>
  );
  }
}
export default SimpleCard;