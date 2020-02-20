import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './shop.reducer';
import { IShop } from 'app/shared/model/shop.model';
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IShopUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IShopUpdateState {
  isNew: boolean;
}

export class ShopUpdate extends React.Component<IShopUpdateProps, IShopUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { shopEntity } = this.props;
      const entity = {
        ...shopEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
    }
  };

  handleClose = () => {
    this.props.history.push('/shop');
  };

  render() {
    const { shopEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="shopApp.shop.home.createOrEditLabel">Create or edit a Shop</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : shopEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="shop-id">ID</Label>
                    <AvInput id="shop-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="nameLabel" for="shop-name">
                    Name
                  </Label>
                  <AvField id="shop-name" type="text" name="name" />
                </AvGroup>
                <AvGroup>
                  <Label id="descriptionLabel" for="shop-description">
                    Description
                  </Label>
                  <AvField id="shop-description" type="text" name="description" />
                </AvGroup>
                <AvGroup>
                  <Label id="coordinatesLabel" for="shop-coordinates">
                    Coordinates
                  </Label>
                  <AvField id="shop-coordinates" type="text" name="coordinates" />
                </AvGroup>
                <AvGroup>
                  <Label id="cityLabel" for="shop-city">
                    City
                  </Label>
                  <AvField id="shop-city" type="text" name="city" />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/shop" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />
                  &nbsp; Save
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  shopEntity: storeState.shop.entity,
  loading: storeState.shop.loading,
  updating: storeState.shop.updating,
  updateSuccess: storeState.shop.updateSuccess
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ShopUpdate);
