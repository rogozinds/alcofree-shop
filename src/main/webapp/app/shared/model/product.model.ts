export interface IProduct {
  id?: number;
  name?: string;
  description?: string;
  img_url?: string;
  comment?: string;
}

export const defaultValue: Readonly<IProduct> = {};
