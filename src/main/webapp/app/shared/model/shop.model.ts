export interface IShop {
  id?: number;
  name?: string;
  description?: string;
  coordinates?: string;
  city?: string;
}

export const defaultValue: Readonly<IShop> = {};
