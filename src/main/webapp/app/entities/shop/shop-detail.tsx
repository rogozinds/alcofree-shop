import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './shop.reducer';
import { IShop } from 'app/shared/model/shop.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IShopDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ShopDetail extends React.Component<IShopDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { shopEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Shop [<b>{shopEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="name">Name</span>
            </dt>
            <dd>{shopEntity.name}</dd>
            <dt>
              <span id="description">Description</span>
            </dt>
            <dd>{shopEntity.description}</dd>
            <dt>
              <span id="coordinates">Coordinates</span>
            </dt>
            <dd>{shopEntity.coordinates}</dd>
            <dt>
              <span id="city">City</span>
            </dt>
            <dd>{shopEntity.city}</dd>
          </dl>
          <Button tag={Link} to="/shop" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>
          &nbsp;
          <Button tag={Link} to={`/shop/${shopEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ shop }: IRootState) => ({
  shopEntity: shop.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ShopDetail);
