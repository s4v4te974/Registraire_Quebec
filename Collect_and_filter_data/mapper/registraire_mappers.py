import pandas as pd
from pandas import Timestamp

default_value_str: str = "N/A"
default_value_char: str = " "
default_value_numeric: int = 0
default_date: Timestamp = pd.to_datetime('1900-01-01')


def map_entreprise(df_entreprise):
    df_entreprise['neq'] = df_entreprise['neq'].fillna(default_value_str)
    df_entreprise['ind_fail'] = df_entreprise['ind_fail'].fillna(default_value_char)
    df_entreprise['dat_immat'] = pd.to_datetime(df_entreprise['dat_immat'], errors='coerce').fillna(default_date)
    df_entreprise['cod_regim_juri'] = (pd.to_numeric(df_entreprise['cod_regim_juri'], errors='coerce')
                                       .fillna(default_value_numeric))
    df_entreprise['cod_intval_emplo_que'] = df_entreprise['cod_intval_emplo_que'].fillna(default_value_str)
    df_entreprise['dat_cess_prevu'] = pd.to_datetime(df_entreprise['dat_cess_prevu'], errors='coerce').fillna(
        default_date)
    df_entreprise['cod_stat_immat'] = df_entreprise['cod_stat_immat'].fillna(default_value_str)
    df_entreprise['cod_forme_juri'] = df_entreprise['cod_forme_juri'].fillna(default_value_str)
    df_entreprise['dat_stat_immat'] = pd.to_datetime(df_entreprise['dat_stat_immat'], errors='coerce').fillna(
        default_date)
    df_entreprise['cod_regim_juri_consti'] = (pd.to_numeric(df_entreprise['cod_regim_juri_consti'])
                                              .fillna(default_value_numeric))
    df_entreprise['dat_depo_declr'] = pd.to_datetime(df_entreprise['dat_depo_declr'], errors='coerce').fillna(
        default_date)
    df_entreprise['an_decl'] = pd.to_numeric(df_entreprise['an_decl'], errors='coerce').fillna(default_value_numeric)
    df_entreprise['an_prod'] = pd.to_numeric(df_entreprise['an_prod'], errors='coerce').fillna(default_value_numeric)
    df_entreprise['dat_limit_prod'] = pd.to_datetime(df_entreprise['dat_limit_prod'], errors='coerce').fillna(
        default_date)
    df_entreprise['an_prod_pre'] = pd.to_numeric(df_entreprise['an_prod_pre'], errors='coerce').fillna(
        default_value_numeric)
    df_entreprise['dat_limit_prod_pre'] = pd.to_datetime(df_entreprise['dat_limit_prod_pre'], errors='coerce').fillna(
        default_date)
    df_entreprise['dat_maj_index_nom'] = pd.to_datetime(df_entreprise['dat_maj_index_nom'], errors='coerce').fillna(
        default_date)
    df_entreprise['cod_act_econ_cae'] = (pd.to_numeric(df_entreprise['cod_act_econ_cae'], errors='coerce')
                                         .fillna(default_value_numeric))
    df_entreprise['no_act_econ_assuj'] = pd.to_numeric(df_entreprise['no_act_econ_assuj'], errors='coerce').fillna(
        default_value_numeric)
    df_entreprise['desc_act_econ_assuj'] = df_entreprise['desc_act_econ_assuj'].fillna(default_value_str)
    df_entreprise['cod_act_econ_cae2'] = (pd.to_numeric(df_entreprise['cod_act_econ_cae2'], errors='coerce')
                                          .fillna(default_value_numeric))
    df_entreprise['no_act_econ_assuj2'] = pd.to_numeric(df_entreprise['no_act_econ_assuj2'], errors='coerce').fillna(
        default_value_numeric)
    df_entreprise['desc_act_econ_assuj2'] = df_entreprise['desc_act_econ_assuj2'].fillna(default_value_str)
    df_entreprise['nom_loclt_consti'] = df_entreprise['nom_loclt_consti'].fillna(default_value_str)
    df_entreprise['dat_consti'] = pd.to_datetime(df_entreprise['dat_consti'], errors='coerce').fillna(default_date)
    df_entreprise["ind_conven_unmn_actnr"] = df_entreprise['ind_conven_unmn_actnr'].fillna(default_value_char)
    df_entreprise['ind_ret_tout_pouvr'] = df_entreprise['ind_ret_tout_pouvr'].fillna(default_value_char)
    df_entreprise['ind_limit_resp'] = df_entreprise['ind_limit_resp'].fillna(default_value_char)
    df_entreprise['dat_deb_resp'] = pd.to_datetime(df_entreprise['dat_deb_resp'], errors='coerce').fillna(default_date)
    df_entreprise['dat_fin_resp'] = pd.to_datetime(df_entreprise['dat_fin_resp'], errors='coerce').fillna(default_date)
    df_entreprise['objet_soc'] = df_entreprise['objet_soc'].fillna(default_value_str)
    df_entreprise['no_mtr_volont'] = df_entreprise['no_mtr_volont'].fillna(default_value_str)
    df_entreprise['adr_domcl_adr_disp'] = df_entreprise['adr_domcl_adr_disp'].fillna(default_value_char)
    df_entreprise['adr_domcl_lign1_adr'] = df_entreprise['adr_domcl_lign1_adr'].fillna(default_value_str)
    df_entreprise['adr_domcl_lign2_adr'] = df_entreprise['adr_domcl_lign2_adr'].fillna(default_value_str)
    df_entreprise['adr_domcl_lign3_adr'] = df_entreprise['adr_domcl_lign3_adr'].fillna(default_value_str)
    df_entreprise['adr_domcl_lign4_adr'] = df_entreprise['adr_domcl_lign4_adr'].fillna(default_value_str)
    return df_entreprise


def map_etablissement(df_etablissement):
    df_etablissement['neq'] = pd.to_numeric(df_etablissement['neq'], errors='coerce').fillna(default_value_str)
    df_etablissement['no_suf_etab'] = pd.to_numeric(df_etablissement['no_suf_etab'], errors='coerce').fillna(
        default_value_numeric)
    df_etablissement['ind_etab_princ'] = df_etablissement['ind_etab_princ'].fillna(default_value_char)
    df_etablissement['ind_salon_bronz'] = df_etablissement['ind_salon_bronz'].fillna(default_value_char)
    df_etablissement['ind_vente_tabac_detl'] = df_etablissement['ind_vente_tabac_detl'].fillna(default_value_char)
    df_etablissement['ind_disp'] = df_etablissement['ind_disp'].fillna(default_value_char)
    df_etablissement['lign1_adr'] = df_etablissement['lign1_adr'].fillna(default_value_str)
    df_etablissement['lign2_adr'] = df_etablissement['lign2_adr'].fillna(default_value_str)
    df_etablissement['lign3_adr'] = df_etablissement['lign3_adr'].fillna(default_value_str)
    df_etablissement['lign4_adr'] = df_etablissement['lign4_adr'].fillna(default_value_str)
    df_etablissement['cod_act_econ'] = (pd.to_numeric(df_etablissement['cod_act_econ'], errors='coerce')
                                        .fillna(default_value_numeric))
    df_etablissement['desc_act_econ_etab'] = df_etablissement['desc_act_econ_etab'].fillna(default_value_str)
    df_etablissement['no_act_econ_etab'] = pd.to_numeric(df_etablissement['no_act_econ_etab'], errors='coerce').fillna(
        default_value_numeric)
    df_etablissement['cod_act_econ2'] = (pd.to_numeric(df_etablissement['cod_act_econ2'], errors='coerce')
                                         .fillna(default_value_numeric))
    df_etablissement['desc_act_econ_etab2'] = df_etablissement['desc_act_econ_etab2'].fillna(default_value_str)
    df_etablissement['no_act_econ_etab2'] = pd.to_numeric(df_etablissement['no_act_econ_etab2'],
                                                          errors='coerce').fillna(default_value_numeric)
    df_etablissement['nom_etab'] = df_etablissement['nom_etab'].fillna(default_value_str)
    return df_etablissement


def map_conti_transfo(df_conti_transfo):
    df_conti_transfo['neq'] = df_conti_transfo['neq'].fillna(default_value_str)
    df_conti_transfo['cod_typ_chang'] = df_conti_transfo['cod_typ_chang'].fillna(default_value_str)
    df_conti_transfo['cod_regim_juri'] = (pd.to_numeric(df_conti_transfo['cod_regim_juri'], errors='coerce')
                                          .fillna(default_value_numeric))
    df_conti_transfo['autr_regim_juri'] = df_conti_transfo['autr_regim_juri'].fillna(default_value_str)
    df_conti_transfo['nom_loclt'] = df_conti_transfo['nom_loclt'].fillna(default_value_str)
    df_conti_transfo['dat_efctvt'] = df_conti_transfo['dat_efctvt'].fillna(pd.to_datetime(default_date))
    return df_conti_transfo


def map_fu_sci(df_fu_sci):
    df_fu_sci['neq'] = df_fu_sci['neq'].fillna(default_value_str)
    df_fu_sci['neq_assuj_rel'] = df_fu_sci['neq_assuj_rel'].fillna(default_value_str)
    df_fu_sci['denomn_soc'] = df_fu_sci['denomn_soc'].fillna(default_value_str)
    df_fu_sci['cod_rela_assuj'] = df_fu_sci['cod_rela_assuj'].fillna(default_value_str)
    df_fu_sci['lign1_adr'] = df_fu_sci['lign1_adr'].fillna(default_value_str)
    df_fu_sci['lign2_adr'] = df_fu_sci['lign2_adr'].fillna(default_value_str)
    df_fu_sci['lign3_adr'] = df_fu_sci['lign3_adr'].fillna(default_value_str)
    df_fu_sci['lign4_adr'] = df_fu_sci['lign4_adr'].fillna(default_value_str)
    df_fu_sci['dat_efctvt'] = pd.to_datetime(df_fu_sci['dat_efctvt'], errors='coerce').fillna(default_date)
    df_fu_sci['ind_disp'] = df_fu_sci['ind_disp'].fillna(default_value_char)
    return df_fu_sci


def map_nom(df_nom):
    df_nom['neq'] = df_nom['neq'].fillna(default_value_str)
    df_nom['nom_assuj'] = df_nom['nom_assuj'].fillna(default_value_str)
    df_nom['nom_assuj_lang_etrng'] = df_nom['nom_assuj_lang_etrng'].fillna(default_value_str)
    df_nom['stat_nom'] = df_nom['stat_nom'].fillna(default_value_str)
    df_nom['typ_nom_assuj'] = df_nom['typ_nom_assuj'].fillna(default_value_str)
    df_nom['dat_init_nom_assuj'] = pd.to_datetime(df_nom['dat_init_nom_assuj'], errors='coerce').fillna(default_date)
    df_nom['dat_fin_nom_assuj'] = pd.to_datetime(df_nom['dat_fin_nom_assuj'], errors='coerce').fillna(default_date)
    return df_nom


def map_domaine_valeur(df_domaine):
    df_domaine['cod_dom_val'] = pd.to_numeric(df_domaine['cod_dom_val'], errors='coerce').fillna(default_value_numeric)
    df_domaine['val_dom_fran'] = df_domaine['val_dom_fran'].fillna(default_value_str)
    return df_domaine
