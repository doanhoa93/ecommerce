package com.framgia.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.framgia.bean.CartInfo;
import com.framgia.bean.CategoryInfo;
import com.framgia.bean.CommentInfo;
import com.framgia.bean.ImageInfo;
import com.framgia.bean.OrderInfo;
import com.framgia.bean.OrderProductInfo;
import com.framgia.bean.ProductInfo;
import com.framgia.bean.PromotionInfo;
import com.framgia.bean.RecentInfo;
import com.framgia.bean.UserInfo;
import com.framgia.helper.ModelToBean;
import com.framgia.helper.ProductFilter;
import com.framgia.model.Category;
import com.framgia.model.OrderProduct;
import com.framgia.model.Product;
import com.framgia.service.ProductService;

public class ProductServiceImpl extends BaseServiceImpl implements ProductService {

	@Override
	public List<OrderInfo> getOrders(Integer productId) {
		try {
			List<OrderProduct> orderProducts = getProductDAO().getOrderProducts(productId);
			return (List<OrderInfo>) orderProducts.stream().map(orderProduct -> {
				return ModelToBean.toOrderInfo(orderProduct.getOrder());
			}).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<CartInfo> getCarts(Integer productId) {
		try {
			return getProductDAO().getCarts(productId).stream().map(ModelToBean::toCartInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<UserInfo> getOrderedUser(Integer productId) {
		try {
			return getOrders(productId).stream().map(OrderInfo::getUser).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<OrderProductInfo> getOrderProducts(Integer productId) {
		try {
			return getProductDAO().getOrderProducts(productId).stream().map(ModelToBean::toOrderProductInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<CommentInfo> getComments(Integer productId) {
		try {
			return getProductDAO().getComments(productId).stream().map(ModelToBean::toCommentInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<ImageInfo> getImages(Integer productId) {
		try {
			return getProductDAO().getImages(productId).stream().map(ModelToBean::toImageInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public RecentInfo getRecent(Integer productId) {
		try {
			return ModelToBean.toRecentInfo(getProductDAO().getRecent(productId));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public PromotionInfo getPromotion(Integer productId) {
		try {
			return ModelToBean.toPromotionInfo(getProductDAO().getPromotion(productId));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public CategoryInfo getCategory(Integer productId) {
		try {
			return ModelToBean.toCategoryInfo(getProductDAO().getCategory(productId));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public ProductInfo findBy(String attribute, Serializable key, boolean lock) {
		try {
			return ModelToBean.toProductInfo(getProductDAO().findBy(attribute, key, lock));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public ProductInfo findById(Serializable key) {
		try {
			return ModelToBean.toProductInfo(getProductDAO().findById(key));
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public boolean delete(ProductInfo entity) {
		try {
			getProductDAO().delete(toProduct(entity));
			return true;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public ProductInfo saveOrUpdate(ProductInfo entity) {
		try {
			Product product = getProductDAO().saveOrUpdate(toProduct(entity));
			return ModelToBean.toProductInfo(product);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public List<ProductInfo> getObjects() {
		try {
			return getProductDAO().getObjects().stream().map(ModelToBean::toProductInfo).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<ProductInfo> getObjectsByIds(List<Integer> keys) {
		try {
			return getProductDAO().getObjectsByIds(keys).stream().map(ModelToBean::toProductInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<ProductInfo> getObjects(int off, int limit) {
		try {
			return getProductDAO().getObjects(off, limit).stream().map(ModelToBean::toProductInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<ProductInfo> getProducts(Integer categoryId, String page, int limit) {
		try {
			int off;
			if (StringUtils.isEmpty(page))
				off = 0;
			else
				off = (Integer.parseInt(page) - 1) * limit;

			return getProductDAO().filterProducts(categoryId, new ProductFilter(null, null, null), off, limit).stream()
			        .map(ModelToBean::toProductInfo).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<ProductInfo> filterProducts(Integer categoryId, ProductFilter productFilter, String page,
	        Integer limit) {
		try {
			int off;
			if (StringUtils.isEmpty(page)) {
				off = 0;
			} else
				off = (Integer.parseInt(page) - 1) * limit;

			return getProductDAO().filterProducts(categoryId, productFilter, off, limit).stream()
			        .map(ModelToBean::toProductInfo).collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	// ----------------- PRIVATE -------------------------------------
	private Product toProduct(ProductInfo productInfo) {
		Product product = getProductDAO().getFromSession(productInfo.getId());
		if (product == null) {
			product = new Product();
			product.setId(productInfo.getId());
			product.setCategory(new Category(productInfo.getCategoryId()));
		}

		product.setAvatar(productInfo.getAvatar());
		product.setInformation(productInfo.getInformation());
		product.setIsPromotion(productInfo.getIsPromotion());
		product.setName(productInfo.getName());
		product.setNumber(productInfo.getNumber());
		product.setPrice(productInfo.getPrice());
		product.setPromotionId(productInfo.getPromotionId());
		product.setRating(productInfo.getRating());
		product.setSaleOf(productInfo.getSaleOf());
		return product;
	}
}
