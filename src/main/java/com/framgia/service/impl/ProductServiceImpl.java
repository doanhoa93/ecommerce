package com.framgia.service.impl;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

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
import com.framgia.helper.UploadFile;
import com.framgia.model.Category;
import com.framgia.model.Image;
import com.framgia.model.OrderProduct;
import com.framgia.model.Product;
import com.framgia.model.Promotion;
import com.framgia.model.Recent;
import com.framgia.service.ProductService;

public class ProductServiceImpl extends BaseServiceImpl implements ProductService {

	@Autowired
	private UploadFile uploadFile;

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
			ProductInfo productInfo = ModelToBean.toProductInfo(getProductDAO().findBy(attribute, key, lock));
			Promotion promotion = getPromotionDAO().findById(productInfo.getPromotionId());
			if (promotion != null)
				productInfo.setPromotion(ModelToBean.toPromotionInfo(promotion));
			return productInfo;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public ProductInfo findById(Serializable key) {
		try {
			return findBy("id", key, true);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public boolean delete(ProductInfo entity) {
		try {
			List<OrderProduct> orderProducts = getOrderProductDAO().getOrderProducts(entity.getId());
			for (OrderProduct orderProduct : orderProducts)
				getOrderProductDAO().delete(orderProduct);

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

	@SuppressWarnings("unchecked")
	@Override
	public boolean createProduct(ProductInfo productInfo) {
		List<String> imageURLs = new ArrayList<>();
		try {
			if (!productInfo.getAvatarFile().isEmpty()) {
				Map<String, String> map = uploadFile.upload(productInfo.getAvatarFile());
				productInfo.setAvatar(map.get("url"));
			}

			List<Integer> statuses = productInfo.getImagesStatus();
			List<MultipartFile> images = productInfo.getImageFiles();
			for (int i = 0; i < statuses.size(); i++) {
				if (statuses.get(i) == 0 && !images.get(i).isEmpty()) {
					Map<String, String> map = uploadFile.upload(images.get(i));
					imageURLs.add((String) map.get("url"));
				}
			}
		} catch (IOException e) {
			return false;
		}

		try {
			Product product = new Product();
			product.setName(productInfo.getName());
			product.setCategory(getCategoryDAO().findById(productInfo.getCategoryId()));
			product.setInformation(productInfo.getInformation());
			product.setNumber(productInfo.getNumber());
			product.setPrice(productInfo.getPrice());
			product.setAvatar(productInfo.getAvatar());
			if (productInfo.getIsPromotion()) {
				product.setIsPromotion(true);
				product.setPromotionId(productInfo.getPromotionId());
				product.setSaleOff(productInfo.getSaleOff());
			}
			product.setCreatedAt(new Date());
			product = getProductDAO().saveOrUpdate(product);

			Recent recent = new Recent();
			recent.setProduct(product);
			recent.setCreatedAt(new Date());
			getRecentDAO().saveOrUpdate(recent);

			for (String imageURL : imageURLs) {
				Image image = new Image();
				image.setImage(imageURL);
				image.setProduct(product);
				getImageDAO().saveOrUpdate(image);
			}

			productInfo.setId(product.getId());
			return true;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateProduct(ProductInfo oldProduct, ProductInfo newProduct) {
		List<String> imageURLs = new ArrayList<>();
		try {
			if (!newProduct.getAvatarFile().isEmpty()) {
				Map<String, String> map = uploadFile.upload(newProduct.getAvatarFile());
				newProduct.setAvatar(map.get("url"));
			}

			List<Integer> statuses = newProduct.getImagesStatus();
			List<MultipartFile> images = newProduct.getImageFiles();
			for (int i = 0; i < statuses.size(); i++) {
				if (statuses.get(i) == 0 && !images.get(i).isEmpty()) {
					Map<String, String> map = uploadFile.upload(images.get(i));
					imageURLs.add((String) map.get("url"));
				}
			}
		} catch (IOException e) {
			return false;
		}

		try {
			Product product = getProductDAO().findById(oldProduct.getId());
			product.setName(newProduct.getName());
			product.setPrice(newProduct.getPrice());
			product.setCategory(getCategoryDAO().findById(newProduct.getCategoryId()));
			product.setInformation(newProduct.getInformation());
			product.setNumber(newProduct.getNumber());
			if (!newProduct.getAvatarFile().isEmpty())
				product.setAvatar(newProduct.getAvatar());
			if (newProduct.getIsPromotion()) {
				product.setIsPromotion(true);
				product.setPromotionId(newProduct.getPromotionId());
				product.setSaleOff(newProduct.getSaleOff());
			}
			getProductDAO().saveOrUpdate(product);

			List<Integer> statuses = newProduct.getImagesStatus();
			List<Integer> ids = newProduct.getImageIds();
			int indexURL = 0;
			for (int i = 0; i < statuses.size(); i++) {
				if (statuses.get(i) == -1) {
					Image image = getImageDAO().findById(ids.get(i));
					if (image != null)
						getImageDAO().delete(image);
				} else if (statuses.get(i) == 0) {
					Image image = new Image();
					image.setImage(imageURLs.get(indexURL));
					image.setProduct(product);
					getImageDAO().saveOrUpdate(image);
					indexURL++;
				}
			}

			return true;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public List<ProductInfo> hotProducts(int limit) {
		try {
			return getProductDAO().hotProducts(limit).stream().map(ModelToBean::toProductInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<ProductInfo> recentProducts(Date date, int limit) {
		try {
			return getProductDAO().recentProducts(date, limit).stream().map(ModelToBean::toProductInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<ProductInfo> randomProducts(int limit) {
		try {
			return getProductDAO().randomProducts(limit).stream().map(ModelToBean::toProductInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public boolean updateRecent(ProductInfo productInfo) {
		try {
			Product product = getProductDAO().findById(productInfo.getId());
			Recent recent = new Recent();
			recent.setCreatedAt(new Date());
			recent.setProduct(product);
			getRecentDAO().saveOrUpdate(recent);
			return true;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public List<ProductInfo> getNewProducts(Date date, int limit) {
		try {
			return getProductDAO().getNewObjects(date, limit).stream().map(ModelToBean::toProductInfo)
			        .collect(Collectors.toList());
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public List<ProductInfo> getProducts(Integer categoryId, int off, int limit, Order order) {
		try {
			return getProductDAO().getProducts(categoryId, off, limit, order).stream().map(ModelToBean::toProductInfo)
			        .collect(Collectors.toList());
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
		product.setSaleOff(productInfo.getSaleOff());
		return product;
	}
}
